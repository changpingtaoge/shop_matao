package com.jy.firsttest.shop.bean;

/**
 * 登录所需的JavaBean对象
 */
public class LoginBean {


    /**
     * errno : 0
     * errmsg :
     * data : {"code":200,"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo4MywiaWF0IjoxNTg0NDI0ODE3fQ.0JJG1yzi-qNzwc2XWIyw_tVT-h6Wbp_yc73iDabzMic","userInfo":{"id":83,"username":"4c3a0dc6-6226-47f3-8f18-296a97631047","nickname":"jiyun1234","gender":0,"avatar":"","birthday":0}}
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
         * code : 200
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo4MywiaWF0IjoxNTg0NDI0ODE3fQ.0JJG1yzi-qNzwc2XWIyw_tVT-h6Wbp_yc73iDabzMic
         * userInfo : {"id":83,"username":"4c3a0dc6-6226-47f3-8f18-296a97631047","nickname":"jiyun1234","gender":0,"avatar":"","birthday":0}
         */

        private int code;
        private String token;
        private UserInfoBean userInfo;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

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
             * id : 83
             * username : 4c3a0dc6-6226-47f3-8f18-296a97631047
             * nickname : jiyun1234
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
