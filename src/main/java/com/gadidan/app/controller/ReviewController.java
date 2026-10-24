package com.gadidan.app.controller;

import com.gadidan.app.common.Category;
import com.gadidan.app.dto.ReviewDto;
import com.gadidan.app.repository.ReviewRepository;
import com.gadidan.app.repository.impl.ReviewRepositoryImpl;
import com.gadidan.app.view.ConsoleView;

import java.util.Arrays;
import java.util.List;

public class ReviewController {
    private ReviewRepository repository;

    public ReviewController() {
        repository = new ReviewRepositoryImpl();
    }

    public void findReviews() {
        // TODO: 반환된 리스트를 사용자에게 출력하는 기능 구현
        List<ReviewDto> results = repository.findReviews();
        if (!results.isEmpty()) {
            ConsoleView.displayReviewList(results);
        } else {
            ConsoleView.displayMessage("현재 등록된 리뷰가 없어서 출력이 불가능합니다.");
        }
    }

    public void findReviewsByScoreDesc() {
        // TODO: 반환된 리스트를 사용자에게 출력하는 기능 구현
        List<ReviewDto> results = repository.findReviewsByScoreDesc();
        if (!results.isEmpty()) {
            ConsoleView.displayReviewList(repository.findReviewsByScoreDesc());
        } else {
            ConsoleView.displayPrompt("현재 등록된 리뷰가 없어서 출력이 불가능합니다.");
        }
    }

    public void findReviewsByCategory(Category category) {
        List<ReviewDto> results = repository.findReviewsByCategory(category);

        if (!results.isEmpty()) {
            ConsoleView.displayReviewList(repository.findReviewsByCategory(category));
        } else {
            ConsoleView.displayPrompt("현재 " + category.getCategory() + " 카테고리에 등록된 리뷰가 없어서 출력이 불가능합니다.");
        }
    }

    public void deleteReview(int userId, String restaurantName){

        int result = repository.deleteReview(userId, restaurantName);

        if(result !=0){
            ConsoleView.displayMessage("리뷰가 삭제되었습니다");
        }else{
            ConsoleView.displayMessage("삭제할 리뷰가 없습니다");
        }
    }

    public void findReviewsUserId(int userId){

        List<ReviewDto> results = repository.findReviewsUserId(userId);
        if(!results.isEmpty()) {
            ConsoleView.displayReviewList(results);
            ConsoleView.displayMessage("삭제하고 싶은 리뷰를 선택해주세요");
        }else{
            ConsoleView.displayMessage("리뷰를 등록한 유저가 아닙니다");
        }
    }
}