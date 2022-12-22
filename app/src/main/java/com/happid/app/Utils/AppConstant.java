package com.happid.app.Utils;

import android.Manifest;


public class AppConstant {;
    public static final int PASSWARD_LENGTH = 6;
    public static final String GPLUS_ID = "638589334207-0gr1d530gj41b2ac11fpf7936a1b0pac.apps.googleusercontent.com";
    public static final String FRAGMENT_VALUE = "fragmentVal";
    public static final String CONSUMER_APPID = "mHwnJQUayP";
    public static boolean isGplusClicked;

    public static String TERMS_AND_CONDITIONS_URL = "https://www.jeeboapp.dz/consumer/termsofuse";
    public static String FAQ_URL = "https://www.jeeboapp.dz/consumer/faq";
    public static String ABOUT_US_URL = "https://www.jeeboapp.dz/consumer/aboutus";



    /*UserPreference Constants*/
     public static String LOGIN_USER_EMAIL = "login_user_email";
     public static String ALGERIA_COUNTRY = "Algeria";
     public static String ACCESS_TOKEN = "access_token";
     public static String LOGIN_USER_NAME = "login_user_name";
     public static String LOGIN_USER_IMAGE = "login_user_image";
     public static String LOGIN_USER_GENDER = "login_user_gender";
     public static String FIREBASE_TOKEN = "firebase_token";
     public static String EMAIL_EXIST = "email_exist";
     public static String FB_TOKEN = "fb_token";
     public static String SOCIAL_TYPE = "social_type";
     public static String TOTAL_PAYABLE = "total_payable";
     public static String LOCATION = "location";
     public static String LATITUDE = "latitude";
     public static String DELIVERY_FEE = "delivery_fee";
     public static String SUB_TOTAL = "sub_total";
     public static String LONGITUDE = "longitude";
     public static String ADDRESS_TYPE = "address_type";
     public static String COUNTRY = "country";
     public static String CART_COUNT = "cart_count";
     public static String MERCHANT_ID = "merchant_id";
     public static String FB_ID = "fb_id";
     public static String LOGIN_USER_ID = "login_user_id";
     public static String USER_NAME = "user_name";
     public static String USER_BIO = "user_bio";
     public static String USER_PHONE = "user_phone";
     public static String USER_IMAGE = "user_image";
     public static String PAYMENT_REQUEST_MODEL = "payment_request_model";
     public static String AFFILIATE_REQUEST_MODEL = "affiliate_request_model";
     public static String ADD_TO_SHOPPING_REQUEST_MODEL = "add_to_shopping_request_model";
     public static String MY_ORDERS_MODEL = "my_orders_model";
     public static String AFFILIATES_MODEL = "affiliates_model";
     public static String PARTICIPANTS_LIST = "participants_list";
     public static String SHARED_SHOP_CAPS = "SHAREDSHOP";
     public static String SHARED_JEEB_CAPS = "SHAREDJEEB";
     public static String SHARED_PRODUCT_CAPS = "SHAREDPRODUCT";
     public static String CHAT_MEDIA_MODEL = "chat_media_model";
     public static String MESSAGE_MODEL = "message_model";


    /*Firebase Constants*/
    public static String MESSAGE_NODE = "Messages";
    public static String THREAD_NODE = "Threads";
    public static String USER_NODE = "Users";
    public static String FIREBASE_AUTH_KEY = "key=AAAAlK7j8r8:APA91bF0YDJDWkzo6rMTtSGyGu1YmUyKfNq5txd-ZzSyA-yQGOVKAzFFTn1fXeseA6DZf40VdKzaAHz_91QjfsUNQmpr_tu8RPCszs-BY4ri6VkP39ZBBnE03NHZJCFrYsUPOXQVNSJ0";


    /*DateFormat Constants*/
    public static String DATE_FORMAT_1 = "MMM dd, yyyy";
    public static String TIME_FORMAT_1 = "hh:mm aa";


    /*NewsFeed Constants*/
    public static String POST_ID = "post_id";
    public static String POSTED_IMAGE_PATH = "posted_image_path";
    public static String POST_TYPE = "post_type";
    public static String UPDATE_POST = "update_post";
    public static String CREATE_POST = "create_post";
    public static String COMMENT_ON_POST = "comment_on_post";
    public static String POST_BEAN = "post_bean";

    /*Other Constants*/
    public static String NAME = "name";
    public static String EMAIL = "email";
    public static String TYPE = "type";
    public static String PHARMACY_ID = "pharmacy_id";
    public static String POSITION = "position";
    public static String SELL_TYPE = "sell_type";
    public static String NODE = "node";
    public static String EXISTING = "existing";
    public static String NEW = "new";
    public static String MESSAGE_LIST = "msg_list";
    public static String ESHOP_CATEGORY = "eshop_category";
    public static String ESHOP_PROFILE_MODEL = "eshop_profile_model";
    public static String ESHOP_ID = "eshop_id";
    public static String ESHOP_PROFILE_ID = "eshop_profile_id";
    public static String ESHOP_NAME = "eshop_name";
    public static String USER_ID = "user_id";
    public static String PRODUCT_ID = "product_id";
    public static String PRODUCT_TYPE = "product_type";
    public static String SELECTED_ADDRESS = "address_type";
    public static String USER_TYPE = "user_type";
    public static String PROFILE_TYPE = "profile_type";
    public static String REQUEST_TYPE = "request_type";
    public static String STRIPE_PUBLISHABLE_KEY = "pk_test_khTaGWQzvpPK8dLXac2jCIyh";
    public static String ADDRESS_MODEL = "address_model";
    public static String ADMIN_ID = "admin_id";
    public static String ADD_MEMBER_TYPE = "add_member_type";
    public static String SHOPPING_LIST_ID = "shopping_list_id";
    public static String SHOPPING_PRODUCT_ID = "shopping_list_product_id";
    public static String MEMBER_LIST = "member_list";
    public static String SUGGESTION_LIST = "suggestion_list";
    public static String PRODCUT_OBJECT = "product_object";
    public static String ORDER_ID = "order_id";
    public static String AFFILIATE_NAME = "affiliate_name";
    public static String AFFILIATE_ID = "affiliate_id";
    public static String CHAT_TYPE = "chat_type";
    public static String NODE_NAME = "node_name";
    public static String PRODUCT_LIST = "product_list";
    public static String BRAND_NAME = "brand_name";
    public static String BRAND_ID = "brand_id";
    public static String CATEGORY_ID = "category_id";
    public static String CHATTING_NODE = "chatting_node";
    public static String DELIVERY = "Delivery";
    public static String CLICK_and_COLLECT = "Click & Collect";
    public static String CATEGORY_NAME = "category_name";
    public static String CAME_FROM= "came_from";
    public static String HEALTH_CAT= "health_cat";
    public static String Alldawa = "Alldawa";



    public interface PreferenceKeeperNames {
        String LOGIN = "login";
        String FIRST_LOGIN = "first_login";
        String ACCESS_TOKEN = "access_token";
        String DEVICE_LANG = "device_lang";
        String USER = "user";
        String UNREAD_NOTIFICATION = "unread_notification";
        String UNREAD_MESSAGE = "unread_msg";

    }

    public interface Languages {
        String ENGLISH="English";
        String ENGLISH_CODE="en";
        String FRENCH="français";
        String FRENCH_CODE="fr";
    }

    public interface BUNDLE_KEY {
        String IS_FROM = "is_from";
    }

    public interface FOOTER_INDEX {
        int HOME = 0;
        int NEWS = 1;
        int CHAT = 2;
        int NOTIFICATION = 3;
        int PROFILE = 4;
    }


    public interface PERMISSION {
        String CAMERA = Manifest.permission.CAMERA;
        String WRITE_EXTERNAL = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        String READ_EXTERNAL = Manifest.permission.READ_EXTERNAL_STORAGE;
    }

    public interface REQUEST_CODE {
        int CAPTURE_IMAGE = 0;
        int GALLARY_IMAGE = 1;
        int CAMERA_PERMISSION = 2;
        int GALLERY_PERMISSION = 3;
        int GALLARY_VIDEO = 4;
        int CAPTURE_VIDEO = 5;
        int MULTIPLE_GALLERY_IMAGE = 6;
        int IMAGE_EDITING_DATA = 7;
        int CAPTURE_IMAGE_PROFILE = 8;
        int GALLARY_IMAGE_PROFILE = 9;
        int UPDATE_POST=10;
        int COMMENT_ON_POST=11;
        int CREATE_POST=12;
        int NEW_MESSAGE=13;

    }

    public interface MEDIA_STATE {
        int CAPTURE_IMAGE = 0;
        int GALLARY_IMAGE = 1;
        int GALLARY_VIDEO = 2;
        int CAPTURE_VIDEO = 3;
        int CAPTURE_IMAGE_PROFILE = 8;
        int GALLARY_IMAGE_PROFILE = 9;
    }

    public interface MEDIA_API {
        String IMAGE = "1";
        String VIDEO = "2";

    }



    public interface MediaType {
        int GROUP_MEDIA = 0;
        int PROFILE_MEDIA = 1;
    }


    public interface SOCAIL_LOGIN_TYPE {
        int FB = 1;
        int GOOGLE= 2;
    }
}

