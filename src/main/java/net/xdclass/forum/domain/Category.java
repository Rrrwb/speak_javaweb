package net.xdclass.forum.domain;

import java.time.LocalDateTime;

/**
 * 论坛开发分类
 * @author ren
 */
public class Category {
    private int id;
    private String name;
    private int weight;
    private LocalDateTime createTime;

    @Override
    public String toString() {
        return "Cateogry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", create_time=" + createTime +
                '}';
    }

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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreate_time(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
