package cn.hujw.image;

/**
 * @author: hujw
 * @date: 2019/8/18
 * @description: 图片加载策略接口
 * @email: hujw_android@163.com
 */
public interface ImageStrategy {

    /**
     * 加载图片
     */
    void load(ImageLoader loader);
}
