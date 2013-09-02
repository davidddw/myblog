package org.tcloud.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.tcloud.controller.vo.CommentInfo;
import org.tcloud.domain.Article;
import org.tcloud.domain.Category;
import org.tcloud.domain.Comment;
import org.tcloud.domain.Tag;
import org.tcloud.support.Pagination;
import org.tcloud.support.StringHelper;
import org.tcloud.support.ValidateCode;

@Controller("nodeController") 
@SessionAttributes(value={"categories", "users", "globalSetting"})
public class NodeController extends BaseController{
	
	private static final String HOME = "/";
	private static final String HOMEPAGE = "/node";
	private static final String CATEGORY = "/node/category/{categoryName:[a-zA-Z0-9-]+}";
	private static final String TAG = "/node/tag/{tagName:[a-zA-Z0-9-]+}";
	private static final String ARTICLE = "/node/article/{articleId}";
	private static final String VALIDATE = "/validateCode";

	
	@RequestMapping(value = {HOME ,HOMEPAGE})
	public String homePage(@ModelAttribute("globalSetting") HashMap<String, String> globalSetting, 
			HttpServletRequest request, Model modelMap) {
		int page = StringHelper.parseWithDefault(request.getParameter("page"), 0);
		int pageSize = StringHelper.parseWithDefault(globalSetting.get("homePageSize"),0);
	    Page<Article> articles = articleService.findLatestArticles(page,pageSize);
	    Pagination pagination = new Pagination(page, (int)articles.getTotalElements(), pageSize);
		modelMap.addAttribute("articles", articles.getContent());
		modelMap.addAttribute("pagination", pagination);
		modelMap.addAttribute("contextPath", request.getRequestURL());
		return "default/home";
	}
	
	@RequestMapping(value = CATEGORY)
	public String categories(@ModelAttribute("globalSetting") HashMap<String, String> globalSetting,
			@PathVariable String categoryName, HttpServletRequest request, Model modelMap) {
		int page = StringHelper.parseWithDefault(request.getParameter("page"), 0);
		int pageSize = StringHelper.parseWithDefault(globalSetting.get("homePageSize"),0);
		Page<Article> articles = articleService.findArticlesFromCategory(new Category(categoryName,null),page,pageSize);
		if (articles.getContent().isEmpty()) {
			return "default/404";
		}else {
			Pagination pagination = new Pagination(page, (int)articles.getTotalElements(), pageSize);
			modelMap.addAttribute("articles", articles.getContent());
			modelMap.addAttribute("pagination", pagination);
			modelMap.addAttribute("contextPath", request.getRequestURL());
			return "default/categories";
		}
	}
	
	@RequestMapping(value=TAG)
	public String tags(@ModelAttribute("globalSetting") HashMap<String, String> globalSetting,
			@PathVariable String tagName, HttpServletRequest request, Model modelMap) {
		int page = StringHelper.parseWithDefault(request.getParameter("page"), 0);
		int pageSize = StringHelper.parseWithDefault(globalSetting.get("homePageSize"),0);
	    Page<Article> articles = articleService.findArticlesFromTag(new Tag(tagName),page,pageSize);
		if (articles.getContent().isEmpty()) {
			return "default/404";
		}else {
			Pagination pagination = new Pagination(page, (int)articles.getTotalElements(), pageSize);
			modelMap.addAttribute("articles", articles.getContent());
			modelMap.addAttribute("pagination", pagination);
			modelMap.addAttribute("contextPath", request.getRequestURL());
			return "default/tags";
		}
	}
	
	@RequestMapping(value = {ARTICLE})
	public String articles(@ModelAttribute("globalSetting") HashMap<String, String> globalSetting, 
			@PathVariable String articleId, HttpServletRequest request, Model modelMap) {
		int myArticleId = StringHelper.parseWithDefault(articleId, 0);
		int pageSize = StringHelper.parseWithDefault(globalSetting.get("commentPageSize"),0);
		Article article = articleService.findById(myArticleId);
		if (article==null) {
			return "default/404";
		}else {
			HttpSession currentUser = request.getSession();
			if(currentUser.getAttribute("visited")==null) {
				article.setPv(article.getPv()+1);
				currentUser.setAttribute("visited", "fLjUfxqXtfNoIldA0A0J");
				currentUser.setMaxInactiveInterval(3600);
				articleService.updateArticle(article);
			}
			int page = StringHelper.parseWithDefault(request.getParameter("page"), 0);
			Pagination pagination = new Pagination(page, (int)article.getCommentCount(), pageSize);
			Page<Comment> comments = commentService.findAllByArticle(article.getId(), page, pageSize);
			modelMap.addAttribute("article", article);
			modelMap.addAttribute("comments", comments.getContent());
			modelMap.addAttribute("pagination", pagination); 
			modelMap.addAttribute("contextPath", request.getRequestURL());
			modelMap.addAttribute("relate_article", articleService.findPrevAndNextArticle(article));
			modelMap.addAttribute("relate_tags", articleService.findArticlesInSameTag(article, 0, 10));
			return "default/article";
		}
	}	
	
	@RequestMapping(value = VALIDATE)
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置浏览器不缓存本页
        response.setHeader("Cache-Control", "no-cache");
        // 生成验证码，写入用户session
        String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_ONLY, 4, null);
        request.getSession().setAttribute("validateCode", verifyCode);
        // 输出验证码给客户端
        response.setContentType("image/jpeg");
        BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, null, null, null, request.getServletContext());
        ImageIO.write(bim, "JPEG", response.getOutputStream());
    }
	
	@RequestMapping(value = "/node/postComment")
	public @ResponseBody Map<String, Object> addComment(CommentInfo commentInfo, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();  
		String validateCode = commentInfo.getValidateCode();
		boolean checkCodeValidate = validateCode.equals(request.getSession().getAttribute("validateCode"));
		String returnMessage = "";
		if (checkCodeValidate) {
			int postId = commentInfo.getPostId();
			Article article = articleService.findById(postId);
			Comment commentId = commentService.addNewComment(commentInfo, article);
			if (commentId!=null){
				returnMessage = "发表评论成功";
			}else{
				returnMessage = "发表评论失败";	
			}
		} else {
			returnMessage = "校验码错误或已经过期";
		}
        map.put("msg", returnMessage);  
        map.put("checkCodeValidate", checkCodeValidate);  
        return map;  
	}
	
	@ModelAttribute("latestArticles")
	public List<Article> getLatestArticles() {
		return articleService.findLatestArticles(0,10).getContent();
	}
	
	@ModelAttribute("mostArticles")
	public List<Article> getMostPopArticles() {
		return articleService.findMostPopArticles(0,10).getContent();
	}
	
	@ModelAttribute("mostComments")
	public List<Comment> getMostComments() {
		return commentService.findLatestComments(0,10).getContent();
	}
}
