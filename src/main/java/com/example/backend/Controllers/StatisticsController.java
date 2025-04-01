// package com.example.backend.Controllers;

// import com.example.backend.Dto.ArticleStatDTO;
// import com.example.backend.Dto.UserStatDTO;
// import com.example.backend.Services.StatisticsService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// import java.util.Map;

// @RestController
// @RequestMapping("/api/statistics")
// public class StatisticsController {

//     @Autowired
//     private StatisticsService statisticsService;

//     // Record article view
//     @PostMapping("/article/{articleId}/view")
//     public ResponseEntity<Void> recordArticleView(
//             @PathVariable Long articleId,
//             @RequestAttribute(value = "userId", required = false) Long userId) {
//         statisticsService.recordArticleView(articleId, userId);
//         return new ResponseEntity<>(HttpStatus.OK);
//     }

//     // Record article download
//     @PostMapping("/article/{articleId}/download")
//     public ResponseEntity<Void> recordArticleDownload(
//             @PathVariable Long articleId,
//             @RequestAttribute(value = "userId", required = false) Long userId) {
//         statisticsService.recordArticleDownload(articleId, userId);
//         return new ResponseEntity<>(HttpStatus.OK);
//     }

//     // Get article statistics
//     @GetMapping("/article/{articleId}")
//     public ResponseEntity<ArticleStatDTO> getArticleStatistics(@PathVariable Long articleId) {
//         ArticleStatDTO stats = statisticsService.getArticleStatistics(articleId);
//         return new ResponseEntity<>(stats, HttpStatus.OK);
//     }

//     // Get user/researcher statistics
//     @GetMapping("/user/{userId}")
//     public ResponseEntity<UserStatDTO> getUserStatistics(@PathVariable Long userId) {
//         UserStatDTO stats = statisticsService.getUserStatistics(userId);
//         return new ResponseEntity<>(stats, HttpStatus.OK);
//     }

//     // Get dashboard statistics (Admin only)
//     @PreAuthorize("hasAuthority('ADMIN')")
//     @GetMapping("/dashboard")
//     public ResponseEntity<Map<String, Object>> getDashboardStatistics() {
//         Map<String, Object> stats = statisticsService.getDashboardStatistics();
//         return new ResponseEntity<>(stats, HttpStatus.OK);
//     }

//     // Get trending articles
//     @GetMapping("/trending")
//     public ResponseEntity<Map<String, Object>> getTrendingArticles() {
//         Map<String, Object> trending = statisticsService.getTrendingArticles();
//         return new ResponseEntity<>(trending, HttpStatus.OK);
//     }

//     // Get user activity over time
//     @PreAuthorize("hasAuthority('ADMIN') or #userId == authentication.principal.id")
//     @GetMapping("/activity/{userId}")
//     public ResponseEntity<Map<String, Object>> getUserActivity(@PathVariable Long userId) {
//         Map<String, Object> activity = statisticsService.getUserActivity(userId);
//         return new ResponseEntity<>(activity, HttpStatus.OK);
//     }
// }