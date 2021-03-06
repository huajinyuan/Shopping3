package cn.zy.base.shopping.http;


/**
 * Created by  on 2017/2/16.
 */

public interface Config {

    String BASE = "https://www.forudropshipping.com";
    int client_id = 3;
    String client_secret = "5UrNvuI10ok6o4IrG21guD6qrV5urPTd05v3VQSI";
    /**
     * 登录接口
     */
    String POST_LOGIN = BASE + "/api/mobile/oauth/token";
    /**
     * 个人信息
     */
    String POST_USERINFO = BASE + "/api/mobile/user/profile";
    /**
     * 注册接口
     */
    String POST_REG = BASE + "/api/mobile/user";
    /**
     * Dashboard
     */
    String DASHBOARD = BASE + "/api/mobile/user/dashboard";
    /**
     * 轮播图
     */
    String SLIDERS = BASE + "/api/mobile/sliders";
    /**
     * 商品列表
     */
    String PRODUCTS = BASE + "/api/mobile/user/products/";
    /**
     * 商品action
     */
    String DELETE_PRODUCT = BASE + "/api/mobile/user/products/{product_id}";
    /**
     * 商品分类
     */
    String PRODUCTS_CATEGORIES = BASE + "/api/mobile/user/products/categories";
    /**
     * GET 获取详情  PUT 更新商品  DELETE 删除商品
     */
    String PRODUCTS_UPDATE = BASE + "/api/mobile/user/products/{product_id}";
    /**
     * Orders
     */
    String ORDER_GROUPS = BASE + "/api/mobile/user/order_groups";
    /**
     * Orders
     */
    String ORDER_GROUPS_PENDING = BASE + "/api/mobile/user/order_groups/pending";
    /**
     * Orders
     */
    String ORDER_BY_ID = BASE + "/api/mobile/user/order_groups/{group_id}/orders/{order_id}";
    /**
     * GET 某个订单列表
     */
    String ORDER_GROUPS_BYID = BASE + "/api/mobile/user/order_groups/{order_id}";
    /**
     * GET 运费计算
     */
    String ORDER_GROUPS_CALCULATE = BASE + "/api/mobile/user/order_groups/shipping_calculate";
    /**
     * GET 支付订单
     */
    String ORDER_GROUPS_PAY = BASE + "/api/mobile/user/payments/order_groups";
    /**
     * DELETE 删除某个订单列表下的某个订单
     * GET 显示某个订单列表下的某个订单
     */
    String ORDER_GROUPS_INFO = BASE + "/api/mobile/user/order_groups/{order_id}/order/{id}";
    /**
     * GET publish designs
     */
    String PUBLISH_DESIGNS = BASE + "/api/mobile/user/public_designs";
    /**
     * GET wishlist_count
     */
    String WISHLIST_COUNT = BASE + "/api/mobile/user/wishlists/count";
    /**
     * GET publish designs
     */
    String PUBLISH_DESIGNS_BYID = BASE + "/api/mobile/user/public_designs/{designs_id}";
    /**
     * get_mywishlists
     */
    String GET_MYWISHLISTS = BASE + "/api/mobile/user/wishlists";
    /**
     * publish_designs_add2wishlists
     */
    String PUBLISH_DESIGNS_ADD2WISHLISTS = BASE + "/api/mobile/user/wishlists/";
    /**
     * publish_designs_add2products
     */
    String PUBLISH_DESIGNS_ADD2PRODUCTS = BASE + "/api/mobile/user/public_designs/products";
    /**
     * delete_mywishlists
     */
    String DELETE_MYWISHLISTS = BASE + "/api/mobile/user/wishlists/{id}";

}
