package com.cooksys.assessment1team3.repositories;


import com.cooksys.assessment1team3.entities.Hashtag;
import com.cooksys.assessment1team3.entities.Tweet;
import com.cooksys.assessment1team3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findAllTweetsByAuthorAndDeletedIsFalseOrderByPostedDesc(User author);

    List<Tweet> findAllByDeletedFalseAndHashtagsOrderByPostedDesc(Hashtag hashtag);

    List<Tweet> findAllByDeletedFalseOrderByPostedDesc();

    List<Tweet> findAllTweetsByAuthorAndDeletedFalse(User author);

    @Query("SELECT t FROM Tweet t WHERE t.inReplyTo.id = :tweetId AND t.deleted = false")
    List<Tweet> findAllRepliesToTweet(@Param("tweetId") Long tweetId);

    @Query("SELECT t FROM Tweet t WHERE t.repostOf.id = :tweetId AND t.deleted = false")
    List<Tweet> findAllRepostsToTweet(@Param("tweetId") Long tweetId);

    Tweet findByIdAndDeletedFalse(Long id);

    @Query("SELECT u FROM User u JOIN u.userLikes t WHERE t.id = :tweetId AND u.deleted = false")
    List<User> findAllUserLikes(@Param("tweetId") Long id);

    List<Tweet> findAllByMentionedUsersAndDeletedFalseOrderByPostedDesc(User user);

    @Query("SELECT u FROM User u JOIN u.userMentions t WHERE t.id = :tweetId AND u.deleted = false")
    List<User> findAllUserMentions(@Param("tweetId") Long id);

}
