package com.example.demo.domain;

/**
 * @Auther: Administrator
 * @Date: 2018/10/11 18:09
 * @Description:
 */

public class Permission {
    private int id;

    //权限名称
    private String name;

    //权限标识
    private String descripion;
    //授权链接
    private String url;
    //父节点id
    private int pid;

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

    public String getDescripion() {
        return descripion;
    }

    public void setDescripion(String descripion) {
        this.descripion = descripion;
    }

    public String getUrl() {
  return url;
 }

 public void setUrl(String url) {
  this.url = url;
 }

 public int getPid() {
  return pid;
 }

 public void setPid(int pid) {
  this.pid = pid;
 }
}
