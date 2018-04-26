package com.crashlytics.issuelist.models;

/**
 * Created by Venkat-Tesark on 25-04-2018.
 */
public class IssueComments {

    /**
     * url : https://api.github.com/repos/crashlytics/secureudid/issues/comments/8873570
     * html_url : https://github.com/crashlytics/secureudid/issues/22#issuecomment-8873570
     * issue_url : https://api.github.com/repos/crashlytics/secureudid/issues/22
     * id : 8873570
     * user : {"login":"mattmassicotte","id":85322,"avatar_url":"https://avatars0.githubusercontent.com/u/85322?v=4","gravatar_id":"","url":"https://api.github.com/users/mattmassicotte","html_url":"https://github.com/mattmassicotte","followers_url":"https://api.github.com/users/mattmassicotte/followers","following_url":"https://api.github.com/users/mattmassicotte/following{/other_user}","gists_url":"https://api.github.com/users/mattmassicotte/gists{/gist_id}","starred_url":"https://api.github.com/users/mattmassicotte/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/mattmassicotte/subscriptions","organizations_url":"https://api.github.com/users/mattmassicotte/orgs","repos_url":"https://api.github.com/users/mattmassicotte/repos","events_url":"https://api.github.com/users/mattmassicotte/events{/privacy}","received_events_url":"https://api.github.com/users/mattmassicotte/received_events","type":"User","site_admin":false}
     * created_at : 2012-09-25T23:09:58Z
     * updated_at : 2012-09-25T23:09:58Z
     * author_association : CONTRIBUTOR
     * body : This sounds like some kind of stack corruption.  I haven't yet been able to reproduce the issue.

     My gut says this has something to do with SUUIDStorageLocationForOwnerKey.  Can you include some source context around your line 108?

     */

    private String url;
    private String html_url;
    private String issue_url;
    private int id;
    private UserBean user;
    private String created_at;
    private String updated_at;
    private String author_association;
    private String body;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getIssue_url() {
        return issue_url;
    }

    public void setIssue_url(String issue_url) {
        this.issue_url = issue_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getAuthor_association() {
        return author_association;
    }

    public void setAuthor_association(String author_association) {
        this.author_association = author_association;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static class UserBean {
        /**
         * login : mattmassicotte
         * id : 85322
         * avatar_url : https://avatars0.githubusercontent.com/u/85322?v=4
         * gravatar_id :
         * url : https://api.github.com/users/mattmassicotte
         * html_url : https://github.com/mattmassicotte
         * followers_url : https://api.github.com/users/mattmassicotte/followers
         * following_url : https://api.github.com/users/mattmassicotte/following{/other_user}
         * gists_url : https://api.github.com/users/mattmassicotte/gists{/gist_id}
         * starred_url : https://api.github.com/users/mattmassicotte/starred{/owner}{/repo}
         * subscriptions_url : https://api.github.com/users/mattmassicotte/subscriptions
         * organizations_url : https://api.github.com/users/mattmassicotte/orgs
         * repos_url : https://api.github.com/users/mattmassicotte/repos
         * events_url : https://api.github.com/users/mattmassicotte/events{/privacy}
         * received_events_url : https://api.github.com/users/mattmassicotte/received_events
         * type : User
         * site_admin : false
         */

        private String login;
        private int id;
        private String avatar_url;
        private String gravatar_id;
        private String url;
        private String html_url;
        private String followers_url;
        private String following_url;
        private String gists_url;
        private String starred_url;
        private String subscriptions_url;
        private String organizations_url;
        private String repos_url;
        private String events_url;
        private String received_events_url;
        private String type;
        private boolean site_admin;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getGravatar_id() {
            return gravatar_id;
        }

        public void setGravatar_id(String gravatar_id) {
            this.gravatar_id = gravatar_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
        }

        public String getFollowers_url() {
            return followers_url;
        }

        public void setFollowers_url(String followers_url) {
            this.followers_url = followers_url;
        }

        public String getFollowing_url() {
            return following_url;
        }

        public void setFollowing_url(String following_url) {
            this.following_url = following_url;
        }

        public String getGists_url() {
            return gists_url;
        }

        public void setGists_url(String gists_url) {
            this.gists_url = gists_url;
        }

        public String getStarred_url() {
            return starred_url;
        }

        public void setStarred_url(String starred_url) {
            this.starred_url = starred_url;
        }

        public String getSubscriptions_url() {
            return subscriptions_url;
        }

        public void setSubscriptions_url(String subscriptions_url) {
            this.subscriptions_url = subscriptions_url;
        }

        public String getOrganizations_url() {
            return organizations_url;
        }

        public void setOrganizations_url(String organizations_url) {
            this.organizations_url = organizations_url;
        }

        public String getRepos_url() {
            return repos_url;
        }

        public void setRepos_url(String repos_url) {
            this.repos_url = repos_url;
        }

        public String getEvents_url() {
            return events_url;
        }

        public void setEvents_url(String events_url) {
            this.events_url = events_url;
        }

        public String getReceived_events_url() {
            return received_events_url;
        }

        public void setReceived_events_url(String received_events_url) {
            this.received_events_url = received_events_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isSite_admin() {
            return site_admin;
        }

        public void setSite_admin(boolean site_admin) {
            this.site_admin = site_admin;
        }
    }
}
