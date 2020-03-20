package com.jy.firsttest.shop.bean;

import java.util.List;

public class GoodsDetailBean {


    /**
     * data : {"attribute":[{"name":"尺寸","value":"160*230cm"},{"name":"颜色","value":"蓝灰格"},{"name":"材质","value":"绒面：100% 聚酯纤维 背面：52% 聚酯纤维/ 48% 棉"},{"name":"产地","value":"中国北京"}],"brand":{},"comment":{"count":0,"data":{}},"gallery":[{"goods_id":1147048,"id":657,"img_desc":"","img_url":"http://yanxuan.nosdn.127.net/cb0fc84a590f63e61b0eb3ee0833fcff.jpg","sort_order":5},{"goods_id":1147048,"id":658,"img_desc":"","img_url":"http://yanxuan.nosdn.127.net/cab7242933b1d129f4f66b05e1652641.jpg","sort_order":5},{"goods_id":1147048,"id":659,"img_desc":"","img_url":"http://yanxuan.nosdn.127.net/e65c7df582c401681bdaa31925cf86e4.jpg","sort_order":5},{"goods_id":1147048,"id":660,"img_desc":"","img_url":"http://yanxuan.nosdn.127.net/586bdb8102b0fa378e554055a5aa58aa.jpg","sort_order":5}],"info":{"add_time":0,"app_exclusive_price":0,"attribute_category":0,"brand_id":0,"category_id":1008002,"counter_price":0,"extra_price":0,"goods_brief":"沉稳双拼色 居家温柔伴护","goods_number":100,"goods_sn":"1147048","goods_unit":"件","id":1147048,"is_app_exclusive":0,"is_delete":0,"is_hot":0,"is_limited":0,"is_new":0,"is_on_sale":1,"keywords":"","list_pic_url":"http://yanxuan.nosdn.127.net/fd7920a2eadd10fa10c0c03959a2abe0.png","name":"简约知性系列居家地毯 蓝灰格","primary_pic_url":"http://yanxuan.nosdn.127.net/3386744ee6833d13a6f6b843cb35f6c6.jpg","primary_product_id":1148177,"promotion_desc":"限时购","promotion_tag":"","retail_price":559,"sell_volume":183,"sort_order":30,"unit_price":0},"issue":[{"answer":"单笔订单金额（不含运费）满88元免邮费；不满88元，每单收取10元运费。 (港澳台地区需满","goods_id":"1127052","id":1,"question":"购买运费如何收取？"},{"answer":"严选默认使用顺丰快递发货（个别商品使用其他快递），配送范围覆盖全国大部分地区（港澳台地区除","goods_id":"1127052","id":2,"question":"使用什么快递发货？"},{"answer":"1.自收到商品之日起30日内，顾客可申请无忧退货，退款将原路返还，不同的银行处理时间不同，","goods_id":"1127052","id":3,"question":"如何申请退货？"},{"answer":"1.如需开具普通发票，请在下单时选择\u201c我要开发票\u201d并填写相关信息（APP仅限2.4.0及以","goods_id":"1127052","id":4,"question":"如何开具发票？"}],"productList":[{"goods_id":1147048,"goods_number":100,"goods_sn":"1147048","goods_specification_ids":"","id":228,"retail_price":559}],"specificationList":[],"userHasCollect":0}
     * errmsg :
     * errno : 0
     */

    private DataBeanX data;
    private String errmsg;
    private int errno;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public static class DataBeanX {
        /**
         * attribute : [{"name":"尺寸","value":"160*230cm"},{"name":"颜色","value":"蓝灰格"},{"name":"材质","value":"绒面：100% 聚酯纤维 背面：52% 聚酯纤维/ 48% 棉"},{"name":"产地","value":"中国北京"}]
         * brand : {}
         * comment : {"count":0,"data":{}}
         * gallery : [{"goods_id":1147048,"id":657,"img_desc":"","img_url":"http://yanxuan.nosdn.127.net/cb0fc84a590f63e61b0eb3ee0833fcff.jpg","sort_order":5},{"goods_id":1147048,"id":658,"img_desc":"","img_url":"http://yanxuan.nosdn.127.net/cab7242933b1d129f4f66b05e1652641.jpg","sort_order":5},{"goods_id":1147048,"id":659,"img_desc":"","img_url":"http://yanxuan.nosdn.127.net/e65c7df582c401681bdaa31925cf86e4.jpg","sort_order":5},{"goods_id":1147048,"id":660,"img_desc":"","img_url":"http://yanxuan.nosdn.127.net/586bdb8102b0fa378e554055a5aa58aa.jpg","sort_order":5}]
         * info : {"add_time":0,"app_exclusive_price":0,"attribute_category":0,"brand_id":0,"category_id":1008002,"counter_price":0,"extra_price":0,"goods_brief":"沉稳双拼色 居家温柔伴护","goods_number":100,"goods_sn":"1147048","goods_unit":"件","id":1147048,"is_app_exclusive":0,"is_delete":0,"is_hot":0,"is_limited":0,"is_new":0,"is_on_sale":1,"keywords":"","list_pic_url":"http://yanxuan.nosdn.127.net/fd7920a2eadd10fa10c0c03959a2abe0.png","name":"简约知性系列居家地毯 蓝灰格","primary_pic_url":"http://yanxuan.nosdn.127.net/3386744ee6833d13a6f6b843cb35f6c6.jpg","primary_product_id":1148177,"promotion_desc":"限时购","promotion_tag":"","retail_price":559,"sell_volume":183,"sort_order":30,"unit_price":0}
         * issue : [{"answer":"单笔订单金额（不含运费）满88元免邮费；不满88元，每单收取10元运费。 (港澳台地区需满","goods_id":"1127052","id":1,"question":"购买运费如何收取？"},{"answer":"严选默认使用顺丰快递发货（个别商品使用其他快递），配送范围覆盖全国大部分地区（港澳台地区除","goods_id":"1127052","id":2,"question":"使用什么快递发货？"},{"answer":"1.自收到商品之日起30日内，顾客可申请无忧退货，退款将原路返还，不同的银行处理时间不同，","goods_id":"1127052","id":3,"question":"如何申请退货？"},{"answer":"1.如需开具普通发票，请在下单时选择\u201c我要开发票\u201d并填写相关信息（APP仅限2.4.0及以","goods_id":"1127052","id":4,"question":"如何开具发票？"}]
         * productList : [{"goods_id":1147048,"goods_number":100,"goods_sn":"1147048","goods_specification_ids":"","id":228,"retail_price":559}]
         * specificationList : []
         * userHasCollect : 0
         */

        private BrandBean brand;
        private CommentBean comment;
        private InfoBean info;
        private int userHasCollect;
        private List<AttributeBean> attribute;
        private List<GalleryBean> gallery;
        private List<IssueBean> issue;
        private List<ProductListBean> productList;
        private List<?> specificationList;

        public BrandBean getBrand() {
            return brand;
        }

        public void setBrand(BrandBean brand) {
            this.brand = brand;
        }

        public CommentBean getComment() {
            return comment;
        }

        public void setComment(CommentBean comment) {
            this.comment = comment;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public int getUserHasCollect() {
            return userHasCollect;
        }

        public void setUserHasCollect(int userHasCollect) {
            this.userHasCollect = userHasCollect;
        }

        public List<AttributeBean> getAttribute() {
            return attribute;
        }

        public void setAttribute(List<AttributeBean> attribute) {
            this.attribute = attribute;
        }

        public List<GalleryBean> getGallery() {
            return gallery;
        }

        public void setGallery(List<GalleryBean> gallery) {
            this.gallery = gallery;
        }

        public List<IssueBean> getIssue() {
            return issue;
        }

        public void setIssue(List<IssueBean> issue) {
            this.issue = issue;
        }

        public List<ProductListBean> getProductList() {
            return productList;
        }

        public void setProductList(List<ProductListBean> productList) {
            this.productList = productList;
        }

        public List<?> getSpecificationList() {
            return specificationList;
        }

        public void setSpecificationList(List<?> specificationList) {
            this.specificationList = specificationList;
        }

        public static class BrandBean {
        }

        public static class CommentBean {
            /**
             * count : 0
             * data : {}
             */

            private int count;
            private DataBean data;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public DataBean getData() {
                return data;
            }

            public void setData(DataBean data) {
                this.data = data;
            }

            public static class DataBean {
                private String content;
                private String add_time;
                private String avatar;
                private String nickname;
                private List<PicListBean> pic_list;

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getAdd_time() {
                    return add_time;
                }

                public void setAdd_time(String add_time) {
                    this.add_time = add_time;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public List<PicListBean> getPic_list() {
                    return pic_list;
                }

                public void setPic_list(List<PicListBean> pic_list) {
                    this.pic_list = pic_list;
                }

                public static class PicListBean {
                    private String comment_id;
                    private int id;
                    private String pic_url;
                    private int sort_order;

                    public String getComment_id() {
                        return comment_id;
                    }

                    public void setComment_id(String comment_id) {
                        this.comment_id = comment_id;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getPic_url() {
                        return pic_url;
                    }

                    public void setPic_url(String pic_url) {
                        this.pic_url = pic_url;
                    }

                    public int getSort_order() {
                        return sort_order;
                    }

                    public void setSort_order(int sort_order) {
                        this.sort_order = sort_order;
                    }
                }
            }
        }

        public static class InfoBean {
            /**
             * add_time : 0
             * app_exclusive_price : 0
             * attribute_category : 0
             * brand_id : 0
             * category_id : 1008002
             * counter_price : 0
             * extra_price : 0
             * goods_brief : 沉稳双拼色 居家温柔伴护
             * goods_number : 100
             * goods_sn : 1147048
             * goods_unit : 件
             * id : 1147048
             * is_app_exclusive : 0
             * is_delete : 0
             * is_hot : 0
             * is_limited : 0
             * is_new : 0
             * is_on_sale : 1
             * keywords :
             * list_pic_url : http://yanxuan.nosdn.127.net/fd7920a2eadd10fa10c0c03959a2abe0.png
             * name : 简约知性系列居家地毯 蓝灰格
             * primary_pic_url : http://yanxuan.nosdn.127.net/3386744ee6833d13a6f6b843cb35f6c6.jpg
             * primary_product_id : 1148177
             * promotion_desc : 限时购
             * promotion_tag :
             * retail_price : 559
             * sell_volume : 183
             * sort_order : 30
             * unit_price : 0
             */

            private int add_time;
            private int app_exclusive_price;
            private int attribute_category;
            private int brand_id;
            private int category_id;
            private int counter_price;
            private int extra_price;
            private String goods_brief;
            private String goods_desc;
            private int goods_number;
            private String goods_sn;
            private String goods_unit;
            private int id;
            private int is_app_exclusive;
            private int is_delete;
            private int is_hot;
            private int is_limited;
            private int is_new;
            private int is_on_sale;
            private String keywords;
            private String list_pic_url;
            private String name;
            private String primary_pic_url;
            private int primary_product_id;
            private String promotion_desc;
            private String promotion_tag;
            private int retail_price;
            private int sell_volume;
            private int sort_order;
            private int unit_price;

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public int getApp_exclusive_price() {
                return app_exclusive_price;
            }

            public void setApp_exclusive_price(int app_exclusive_price) {
                this.app_exclusive_price = app_exclusive_price;
            }

            public int getAttribute_category() {
                return attribute_category;
            }

            public void setAttribute_category(int attribute_category) {
                this.attribute_category = attribute_category;
            }

            public int getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(int brand_id) {
                this.brand_id = brand_id;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public int getCounter_price() {
                return counter_price;
            }

            public void setCounter_price(int counter_price) {
                this.counter_price = counter_price;
            }

            public int getExtra_price() {
                return extra_price;
            }

            public void setExtra_price(int extra_price) {
                this.extra_price = extra_price;
            }

            public String getGoods_brief() {
                return goods_brief;
            }

            public void setGoods_brief(String goods_brief) {
                this.goods_brief = goods_brief;
            }

            public String getGoods_desc() {
                return goods_desc;
            }

            public void setGoods_desc(String goods_desc) {
                this.goods_desc = goods_desc;
            }

            public int getGoods_number() {
                return goods_number;
            }

            public void setGoods_number(int goods_number) {
                this.goods_number = goods_number;
            }

            public String getGoods_sn() {
                return goods_sn;
            }

            public void setGoods_sn(String goods_sn) {
                this.goods_sn = goods_sn;
            }

            public String getGoods_unit() {
                return goods_unit;
            }

            public void setGoods_unit(String goods_unit) {
                this.goods_unit = goods_unit;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIs_app_exclusive() {
                return is_app_exclusive;
            }

            public void setIs_app_exclusive(int is_app_exclusive) {
                this.is_app_exclusive = is_app_exclusive;
            }

            public int getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(int is_delete) {
                this.is_delete = is_delete;
            }

            public int getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(int is_hot) {
                this.is_hot = is_hot;
            }

            public int getIs_limited() {
                return is_limited;
            }

            public void setIs_limited(int is_limited) {
                this.is_limited = is_limited;
            }

            public int getIs_new() {
                return is_new;
            }

            public void setIs_new(int is_new) {
                this.is_new = is_new;
            }

            public int getIs_on_sale() {
                return is_on_sale;
            }

            public void setIs_on_sale(int is_on_sale) {
                this.is_on_sale = is_on_sale;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public String getList_pic_url() {
                return list_pic_url;
            }

            public void setList_pic_url(String list_pic_url) {
                this.list_pic_url = list_pic_url;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrimary_pic_url() {
                return primary_pic_url;
            }

            public void setPrimary_pic_url(String primary_pic_url) {
                this.primary_pic_url = primary_pic_url;
            }

            public int getPrimary_product_id() {
                return primary_product_id;
            }

            public void setPrimary_product_id(int primary_product_id) {
                this.primary_product_id = primary_product_id;
            }

            public String getPromotion_desc() {
                return promotion_desc;
            }

            public void setPromotion_desc(String promotion_desc) {
                this.promotion_desc = promotion_desc;
            }

            public String getPromotion_tag() {
                return promotion_tag;
            }

            public void setPromotion_tag(String promotion_tag) {
                this.promotion_tag = promotion_tag;
            }

            public int getRetail_price() {
                return retail_price;
            }

            public void setRetail_price(int retail_price) {
                this.retail_price = retail_price;
            }

            public int getSell_volume() {
                return sell_volume;
            }

            public void setSell_volume(int sell_volume) {
                this.sell_volume = sell_volume;
            }

            public int getSort_order() {
                return sort_order;
            }

            public void setSort_order(int sort_order) {
                this.sort_order = sort_order;
            }

            public int getUnit_price() {
                return unit_price;
            }

            public void setUnit_price(int unit_price) {
                this.unit_price = unit_price;
            }
        }

        public static class AttributeBean {
            /**
             * name : 尺寸
             * value : 160*230cm
             */

            private String name;
            private String value;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class GalleryBean {
            /**
             * goods_id : 1147048
             * id : 657
             * img_desc :
             * img_url : http://yanxuan.nosdn.127.net/cb0fc84a590f63e61b0eb3ee0833fcff.jpg
             * sort_order : 5
             */

            private int goods_id;
            private int id;
            private String img_desc;
            private String img_url;
            private int sort_order;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg_desc() {
                return img_desc;
            }

            public void setImg_desc(String img_desc) {
                this.img_desc = img_desc;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public int getSort_order() {
                return sort_order;
            }

            public void setSort_order(int sort_order) {
                this.sort_order = sort_order;
            }
        }

        public static class IssueBean {
            /**
             * answer : 单笔订单金额（不含运费）满88元免邮费；不满88元，每单收取10元运费。 (港澳台地区需满
             * goods_id : 1127052
             * id : 1
             * question : 购买运费如何收取？
             */

            private String answer;
            private String goods_id;
            private int id;
            private String question;

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getQuestion() {
                return question;
            }

            public void setQuestion(String question) {
                this.question = question;
            }
        }

        public static class ProductListBean {
            /**
             * goods_id : 1147048
             * goods_number : 100
             * goods_sn : 1147048
             * goods_specification_ids :
             * id : 228
             * retail_price : 559
             */

            private int goods_id;
            private int goods_number;
            private String goods_sn;
            private String goods_specification_ids;
            private int id;
            private int retail_price;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getGoods_number() {
                return goods_number;
            }

            public void setGoods_number(int goods_number) {
                this.goods_number = goods_number;
            }

            public String getGoods_sn() {
                return goods_sn;
            }

            public void setGoods_sn(String goods_sn) {
                this.goods_sn = goods_sn;
            }

            public String getGoods_specification_ids() {
                return goods_specification_ids;
            }

            public void setGoods_specification_ids(String goods_specification_ids) {
                this.goods_specification_ids = goods_specification_ids;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getRetail_price() {
                return retail_price;
            }

            public void setRetail_price(int retail_price) {
                this.retail_price = retail_price;
            }
        }
    }
}
