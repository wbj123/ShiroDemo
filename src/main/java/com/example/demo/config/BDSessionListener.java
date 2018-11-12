package com.example.demo.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Administrator
 * @Date: 2018/10/25 13:15
 * @Description:
 */
public class BDSessionListener implements SessionListener {

 private final AtomicInteger sessionCount = new AtomicInteger(0);

 @Override
 public void onStart(Session session) {

  sessionCount.incrementAndGet();
 }

 @Override
 public void onStop(Session session) {
  sessionCount.decrementAndGet();
 }

 @Override
 public void onExpiration(Session session) {
  sessionCount.decrementAndGet();

 }

 public int getSessionCount() {
  return sessionCount.get();
 }

}
