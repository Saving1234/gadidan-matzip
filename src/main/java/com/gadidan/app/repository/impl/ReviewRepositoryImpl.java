package com.gadidan.app.repository.impl;

import com.gadidan.app.common.Category;
import com.gadidan.app.common.JDBCTemplate;
import com.gadidan.app.dto.RestaurantDto;
import com.gadidan.app.dto.ReviewDto;
import com.gadidan.app.dto.UserDto;
import com.gadidan.app.repository.ReviewRepository;
import com.gadidan.app.view.ConsoleView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

/// ReviewRepositoryImpl 인터페이스를 구현한 클래스입니다.
public class ReviewRepositoryImpl implements ReviewRepository {
    @Override
    public int save(ReviewDto review) {
        return 0;  // todo: 더미값입니다. 수정 예정
    }

    @Override
    public List<ReviewDto> findReviewsByCategory(Category category) {
        Connection con = JDBCTemplate.getConnection();
        PreparedStatement pstmt = null;
        Properties prop = new Properties();
        List<ReviewDto> reviewDtoList = new ArrayList<>();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/gadidan/app/mapper/query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("selectReviewsByCategory"));

            pstmt.setString(1, String.valueOf(category.getCategory()));

            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                RestaurantDto restaurantDto = new RestaurantDto();
                restaurantDto.setRestaurantName(rset.getString("restaurant_name"));
                restaurantDto.setBestMenu(rset.getString("best_menu"));
                restaurantDto.setCategory(rset.getString("category"));
                UserDto userDto = new UserDto();
                userDto.setUserId(rset.getString("user_id"));
                userDto.setName(rset.getString("name"));

                reviewDtoList.add(new ReviewDto(
                        rset.getInt("review_id"),
                        rset.getInt("score"),
                        rset.getString("comment"),
                        rset.getObject("add_date", LocalDate.class),
                        rset.getObject("modify_date", LocalDate.class),
                        userDto,
                        restaurantDto
                ));
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return reviewDtoList;
    }

    @Override
    public List<ReviewDto> findReviewsByScoreDesc() {
        Connection con = JDBCTemplate.getConnection();
        PreparedStatement pstmt = null;
        Properties prop = new Properties();
        List<ReviewDto> reviewDtoList = new ArrayList<>();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/gadidan/app/mapper/query.xml"));
            String query = prop.getProperty("selectReviewsByScoreDesc");

            pstmt = con.prepareStatement(query);

            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                RestaurantDto restaurantDto = new RestaurantDto();
                restaurantDto.setRestaurantName(rset.getString("restaurant_name"));
                restaurantDto.setBestMenu(rset.getString("best_menu"));
                restaurantDto.setCategory(rset.getString("category"));
                UserDto userDto = new UserDto();
                userDto.setUserId(rset.getString("user_id"));
                userDto.setName(rset.getString("name"));

                reviewDtoList.add(new ReviewDto(
                        rset.getInt("review_id"),
                        rset.getInt("score"),
                        rset.getString("comment"),
                        rset.getObject("add_date", LocalDate.class),
                        rset.getObject("modify_date", LocalDate.class),
                        userDto,
                        restaurantDto
                ));

            }

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return reviewDtoList;
    }

    @Override
    public List<ReviewDto> findReviews() {


        Properties prop = new Properties();

        List<ReviewDto> reviewDtoList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rSet = null;

        try {

            con = JDBCTemplate.getConnection();
            prop.loadFromXML(new FileInputStream("src/main/java/com/gadidan/app/mapper/query.xml"));
            String query = prop.getProperty("selectAllReviews");
            pstm = con.prepareStatement(query);
            rSet = pstm.executeQuery();


            while (rSet.next()) {


                UserDto userDto = new UserDto();
                userDto.setUserId(rSet.getString("user_id"));
                userDto.setName(rSet.getString("name"));

                RestaurantDto restaurantDto = new RestaurantDto();
                restaurantDto.setRestaurantName(rSet.getString("restaurant_name"));
                restaurantDto.setBestMenu(rSet.getString("best_menu"));
                restaurantDto.setCategory(rSet.getString("category"));


                reviewDtoList.add(new ReviewDto(
                        rSet.getInt("review_id"),
                        rSet.getInt("score"),
                        rSet.getString("comment"),
                        rSet.getObject("add_date", LocalDate.class),
                        rSet.getObject("modify_date", LocalDate.class),
                        userDto,
                        restaurantDto
                ));


            }


        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTemplate.close(rSet);
            JDBCTemplate.close(pstm);
            JDBCTemplate.close(con);
        }


        return reviewDtoList;
        // todo: 더미값입니다. 수정 예정
    }

    @Override
    public int deleteReview(int userId, String restaurantName) {

        Properties prop = new Properties();

        List<ReviewDto> reviewDtoList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstm = null;
        int result = 0;

        try {

            con = JDBCTemplate.getConnection();
            prop.loadFromXML(new FileInputStream("src/main/java/com/gadidan/app/mapper/query.xml"));
            String query = prop.getProperty("deleteReviewUserId");
            pstm = con.prepareStatement(query);
            pstm.setInt(1, userId);
            pstm.setString(2,restaurantName);
            result = pstm.executeUpdate();


            return result;
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTemplate.close(pstm);
            JDBCTemplate.close(con);
        }
    }

        @Override
    public List<ReviewDto> findReviewsUserId(int userId) {

        Properties prop = new Properties();

        List<ReviewDto> reviewDtoList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rSet = null;

        try {

            con = JDBCTemplate.getConnection();
            prop.loadFromXML(new FileInputStream("src/main/java/com/gadidan/app/mapper/query.xml"));
            String query = prop.getProperty("selectFindReviewsUserId");
            pstm = con.prepareStatement(query);
            pstm.setInt(1,userId);
            rSet = pstm.executeQuery();


            while (rSet.next()) {


                UserDto userDto = new UserDto();
                userDto.setUserId(rSet.getString("user_id"));
                userDto.setName(rSet.getString("name"));

                RestaurantDto restaurantDto = new RestaurantDto();
                restaurantDto.setRestaurantName(rSet.getString("restaurant_name"));
                restaurantDto.setBestMenu(rSet.getString("best_menu"));
                restaurantDto.setCategory(rSet.getString("category"));


                reviewDtoList.add(new ReviewDto(
                        rSet.getInt("review_id"),
                        rSet.getInt("score"),
                        rSet.getString("comment"),
                        rSet.getObject("add_date", LocalDate.class),
                        rSet.getObject("modify_date", LocalDate.class),
                        userDto,
                        restaurantDto
                ));


            }


        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTemplate.close(rSet);
            JDBCTemplate.close(pstm);
            JDBCTemplate.close(con);
        }


        return reviewDtoList;
    }
}
