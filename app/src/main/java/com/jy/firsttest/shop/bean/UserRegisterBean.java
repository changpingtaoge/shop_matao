package com.jy.firsttest.shop.bean;

public class UserRegisterBean {


    /**
     * errno : 0
     * errmsg :
     * data : {"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo2NSwiaWF0IjoxNTg0MDg1Njk4fQ.8K7TU3sD1t4dvEDv27CJmPyb4kMIxgaY5-0-6HucfHM","userInfo":{"id":65,"username":"caf5a88f-7e37-4b27-8f82-67b5ec4a3f35","nickname":"a12456","gender":0,"avatar":"","birthday":0}}
     */

    private int errno;
    private String errmsg;
    private DataBean data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo2NSwiaWF0IjoxNTg0MDg1Njk4fQ.8K7TU3sD1t4dvEDv27CJmPyb4kMIxgaY5-0-6HucfHM
         * userInfo : {"id":65,"username":"caf5a88f-7e37-4b27-8f82-67b5ec4a3f35","nickname":"a12456","gender":0,"avatar":"","birthday":0}
         */

        private String token;
        private UserInfoBean userInfo;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoBean {
            /**
             * id : 65
             * username : caf5a88f-7e37-4b27-8f82-67b5ec4a3f35
             * nickname : a12456
             * gender : 0
             * avatar :
             * birthday : 0
             */

            private int id;
            private String username;
            private String nickname;
            private int gender;
            private String avatar;
            private int birthday;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getBirthday() {
                return birthday;
            }

            public void setBirthday(int birthday) {
                this.birthday = birthday;
            }
        }
    }
}
