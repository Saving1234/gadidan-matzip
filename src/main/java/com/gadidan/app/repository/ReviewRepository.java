package com.gadidan.app.repository;

import com.gadidan.app.common.Category;
import com.gadidan.app.dto.ReviewDto;

import java.util.List;

public interface ReviewRepository {
    /// 리뷰 등록
    /// @param review ReviewDto 객체
    /// @return 영향 받은 행의 수
    int save(ReviewDto review);

    /// 카테고리 별 리뷰 조회
    /// @param category 조회하고자 하는 카테고리
    /// @return 조건에 해당하는 리뷰 리스트
    List<ReviewDto> findReviewsByCategory(Category category);

    /// 별점 순으로 리뷰 조회
    /// @return 조건에 해당하는 리뷰 리스트
    List<ReviewDto> findReviewsByScoreDesc();

    /// 등록 순으로 리뷰 조회
    /// @return 조건에 해당하는 리뷰 리스트
    List<ReviewDto> findReviews();

    /// 리뷰 삭제
    /// @param restaurantName 삭제하고자 하는 레스토랑의 식당명
    /// @param userId 등록한 사용자가 맞는지 확인하는 유저의 아이디
    /// @return 영향 받은 행의 수
    int deleteReview(int userId, String restaurantName);

    /// 본인이 등록한 리뷰목록 조회
    /// @param userId 등록한 사용자가 맞는지 확인하는 유저의 아이디
    List<ReviewDto> findReviewsUserId(int userId);
}
