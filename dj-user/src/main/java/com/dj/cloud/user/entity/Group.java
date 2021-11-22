package com.dj.cloud.user.entity;

import com.dj.cloud.common.base.CommonObject;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "groups")
@EntityListeners(AuditingEntityListener.class)
public class Group extends CommonObject {
    @Id
    @GeneratedValue
    private Integer id;

    private String groupName;

    private String isUse;

    @OneToMany(targetEntity = User.class, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,  CascadeType.MERGE})
    @JoinTable(name="group_user",
            joinColumns = @JoinColumn(name="group_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="user_id", referencedColumnName="id"))
    private List<User> users;

    @OneToMany(targetEntity = Role.class, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,  CascadeType.MERGE})
    @JoinTable(name="group_role",
            joinColumns = @JoinColumn(name="group_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName="id"))
    private Set<Role> roles;

    @CreatedDate
    private Date createTime;

    private String createUser;

    @LastModifiedDate
    private Date updateTime;

    private String updateUser;

    public Group() {
    }

    public Group(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", isUse='" + isUse + '\'' +
                ", users=" + users +
                ", roles=" + roles +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                '}';
    }
}
