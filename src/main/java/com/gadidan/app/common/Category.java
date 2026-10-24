package com.gadidan.app.common;

/// 리뷰 등록 시 식당의 카테고리에 해당하는 열거 클래스
public enum Category {
    KOREAN("한식"),
    CHINESE("중식"),
    JAPANESE("일식"),
    WESTERN("양식"),
    SNACK_FOOD("분식"),
    BUFFET("뷔페"),
    DESSERT("디저트");

    Category(String category) {
        this.category = category;
    }

    private final String category;

    public String getCategory() {
        return category;
    }
}