package com.cfyj.algorithm.questions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeMap;

import lombok.extern.slf4j.Slf4j;

/**
 * 题目:
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个功能：

postTweet(userId, tweetId): 创建一条新的推文
getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
follow(followerId, followeeId): 关注一个用户
unfollow(followerId, followeeId): 取消关注一个用户
示例:

Twitter twitter = new Twitter();

// 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
twitter.postTweet(1, 5);

// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
twitter.getNewsFeed(1);

// 用户1关注了用户2.
twitter.follow(1, 2);

// 用户2发送了一个新推文 (推文id = 6).
twitter.postTweet(2, 6);

// 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
// 推文id6应当在推文id5之前，因为它是在5之后发送的.
twitter.getNewsFeed(1);

// 用户1取消关注了用户2.
twitter.unfollow(1, 2);

// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
// 因为用户1已经不再关注用户2.
twitter.getNewsFeed(1);
 */
/**
 * 解析:
 * 
 * @author chenfeng
 *
 */
@Slf4j
public class Twitter {

	private Map<Integer, TreeMap<Long, Integer>> infosRel = new HashMap(); // 维护消息
	private Map<Integer, Integer> followsRel = new HashMap(); // 维护关注

	// 创建一条新的推文
	public void postTweet(int userId, int tweetId) {
		TreeMap<Long, Integer> infos = infosRel.get(userId);
		if (infos == null) {
			infos = new TreeMap();
			infosRel.put(userId, infos);
		}
		try {
			Thread.sleep(10);//执行太快会导致值覆盖,所以增加休眠时间
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		infos.put(new Date().getTime(), tweetId);
	}

	// 关注
	public void follow(int followerId, int followeeId) {
		followsRel.put(followerId, followeeId);
	}

	// 取消关注
	public void unfollow(int followerId, int followeeId) {
		followsRel.remove(followerId);
	}

	// 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
	public List<Integer> getNewsFeed(int userId) {
		Integer followId = followsRel.get(userId);
		TreeMap<Long, Integer> followInfos = infosRel.get(followId);
		TreeMap<Long, Integer> userInfos = infosRel.get(userId);
		TreeMap<Long, Integer> tmp = new TreeMap();
		List<Integer> resp = new ArrayList(10);

		if (userInfos != null) {
			tmp.putAll(userInfos);
		}
		if (followInfos != null) {
			tmp.putAll(followInfos);
		}

		NavigableSet<Long> keySet = tmp.descendingKeySet();// 从大到小返回列表key
		if (keySet != null) {
			int num = 0;
			Iterator<Long> iterator = keySet.iterator();
			while (iterator.hasNext() && num++ < 10) {
				Long next = iterator.next();
				resp.add(tmp.get(next));
			}
		}

		return resp;
	}

	public List<Integer> getNewsFeed2(int userId) {
		Integer followId = followsRel.get(userId);
		TreeMap<Long, Integer> followInfos = infosRel.get(followId);
		TreeMap<Long, Integer> userInfos = infosRel.get(userId);
		LinkedList<Integer> resp = new LinkedList();
		TreeMap<Long, Integer> tmpMap = new TreeMap();
		if ( followInfos != null) {
			tmpMap.putAll(followInfos);
		}
		if(userInfos!=null){
			tmpMap.putAll(userInfos);
		}
		if (tmpMap != null && tmpMap.size() > 10) {
			// >10
			int num = 0;
			int tmp = 1;
			num = tmpMap.size() - 10;
			for (Long key : tmpMap.keySet()) {
				if (tmp++ > num) {
					resp.addFirst(tmpMap.get(key));
				}
			}

		} else if (tmpMap != null) {
			// <10
			for (Long key : tmpMap.keySet()) {
				resp.addFirst(tmpMap.get(key));
			}
		}
		return resp;
	}

	public static void main(String[] args) {
		Twitter Test5 = new Twitter();
		List<Integer> infos;
		// 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
//		Test5.postTweet(1, 15);
//		Test5.postTweet(1, 16);
//		Test5.postTweet(1, 17);
//		// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
//		infos = Test5.getNewsFeed2(1);
//		log.info("infos-1:{}", infos);
//		// 用户1关注了用户2.
//		Test5.follow(1, 2);
//		// 用户2发送了一个新推文 (推文id = 6).
//		Test5.postTweet(2, 26);
//		Test5.postTweet(2, 29);
//		// 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
//		// 推文id6应当在推文id5之前，因为它是在5之后发送的.
//		infos = Test5.getNewsFeed2(1);
//		log.info("infos-2:{}", infos);
//		// 用户1取消关注了用户2.
//		Test5.unfollow(1, 2);
//		// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
//		// 因为用户1已经不再关注用户2.
//		infos = Test5.getNewsFeed2(1);
//		log.info("infos-3:{}", infos);
		
		Test5.postTweet(1, 5);
		Test5.follow(1, 2);
		Test5.postTweet(2, 6);
		Test5.unfollow(1, 2);
		infos = Test5.getNewsFeed2(1);
		log.info("infos-3:{}", infos);
//		["Twitter","postTweet","getNewsFeed","follow","postTweet","getNewsFeed",
//		 "unfollow","getNewsFeed"]
//				[[],[1,5],[1],[1,2],[2,6],[1],[1,2],[1]]
						
	}

}
