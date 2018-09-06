package com.chad.zhihu.entity.zhihu;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页最新消息实体类
 */
public class HomeInfo implements Parcelable {
    /**
     * date : 20161214
     * stories : [{"images":["http://pic3.zhimg.com/afab44d4ef0758d1940eaccbe622cf9a.jpg"],"type":0,"id":9065007,"ga_prefix":"121415","title":"三国杀的战术和专利竞争"},{"images":["http://pic4.zhimg.com/76197937faa9a8b1005b716b12612a53.jpg"],"type":0,"id":9063416,"ga_prefix":"121414","title":"如何巧妙地达到沟通目的？"},{"images":["http://pic4.zhimg.com/ec1e27a350b31f700d866a600be2354f.jpg"],"type":0,"id":9064857,"ga_prefix":"121413","title":"苹果的
     * AirPods 开卖了，这是用了一个多月的体验"},{"images":["http://pic3.zhimg.com/84d406badd1e58ad3e2afc4127f4178a.jpg"],"type":0,"id":9064184,"ga_prefix":"121412","title":"大误
     * · 一个男人的 100 种死法"},{"images":["http://pic4.zhimg.com/10a91148593f28f42b27d8b3829649b7.jpg"],"type":0,"id":9063457,"ga_prefix":"121411","title":"想在
     * Excel 里少犯错，多用自动化替代人肉操作"},{"images":["http://pic3.zhimg.com/81dcc5a406e23fb60345d9a59eb7f4f2.jpg"],"type":0,"id":9062157,"ga_prefix":"121410","title":"美欧日拒绝承认中国市场经济地位，还能愉快地赚钱吗？"},{"images":["http://pic2.zhimg.com/ae5c912d6c327ceb9d22bcd213ee0799.jpg"],"type":0,"id":9063445,"ga_prefix":"121409","title":"「整个画面白屏的时候，我以为自己真的死了」"},{"images":["http://pic3.zhimg.com/946e7941861fdb50fc49cae3a6de1ad6.jpg"],"type":0,"id":9062771,"ga_prefix":"121408","title":"银行员工怎样拉到真正的第一笔存款？"},{"images":["http://pic4.zhimg.com/7af56a9af569984f29d9682e3755bbfb.jpg"],"type":0,"id":9063370,"ga_prefix":"121407","title":"「投资你两千万，可别拿了钱就跑路啊」"},{"images":["http://pic4.zhimg.com/170ce9d78aab2de932aa479cd14d7ce7.jpg"],"type":0,"id":9061596,"ga_prefix":"121407","title":"怎样解决男男性传播艾滋病疫情上升的问题？"},{"images":["http://pic3.zhimg.com/cfb7ea85402fdb42e335bc696eee5556.jpg"],"type":0,"id":9062473,"ga_prefix":"121407","title":"《你的名字。》里的「口嚼酒」真的存在，还有点甜"},{"images":["http://pic3.zhimg.com/9cbca8cb0f92f979a2ef2e46ddd4f4ea.jpg"],"type":0,"id":9063201,"ga_prefix":"121407","title":"读读日报
     * 24 小时热门 TOP 5 · 最烂的一次约会到底有多烂？"},{"images":["http://pic3.zhimg.com/5abc7bd13ce5a34bfc849068c2a584a6.jpg"],"type":0,"id":9062270,"ga_prefix":"121406","title":"瞎扯
     * · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic3.zhimg.com/65e1e452a62f7e279f516b654b5af3e2.jpg","type":0,"id":9064857,"ga_prefix":"121413","title":"苹果的
     * AirPods 开卖了，这是用了一个多月的体验"},{"image":"http://pic1.zhimg.com/76b28d30f82e95e6d08fa536d52b0a68.jpg","type":0,"id":9062157,"ga_prefix":"121410","title":"美欧日拒绝承认中国市场经济地位，还能愉快地赚钱吗？"},{"image":"http://pic2.zhimg.com/cdde24410b4ec4f61f48f915d2450ccd.jpg","type":0,"id":9062473,"ga_prefix":"121407","title":"《你的名字。》里的「口嚼酒」真的存在，还有点甜"},{"image":"http://pic2.zhimg.com/79941c4337224e9e1021126d650a5a7d.jpg","type":0,"id":9063201,"ga_prefix":"121407","title":"读读日报
     * 24 小时热门 TOP 5 · 最烂的一次约会到底有多烂？"},{"image":"http://pic2.zhimg.com/0995f252d5e81ea61c0d4209ed64ddf1.jpg","type":0,"id":9062187,"ga_prefix":"121317","title":"知乎好问题
     * · 如何教会儿童在突发灾害中保护自己？"}]
     */
    private String date;
    private List<Stories> stories;
    private List<TopStories> top_stories;
    private List<Integer> storiesIds;

    protected HomeInfo(Parcel parcel) {
        date = parcel.readString();
        stories = new ArrayList<>();
        parcel.readList(stories, Stories.class.getClassLoader());
        top_stories = new ArrayList<>();
        parcel.readList(top_stories, Stories.class.getClassLoader());
        storiesIds = new ArrayList<>();
        parcel.readList(storiesIds, Integer.class.getClassLoader());
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setTop_stories(List<TopStories> top_stories) {
        this.top_stories = top_stories;
    }

    public List<TopStories> getTop_stories() {
        return top_stories;
    }

    public void setStoriesIds(List<Integer> storiesIds) {
        this.storiesIds = storiesIds;
    }

    public List<Integer> getStoriesIds() {
        return storiesIds;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeList(stories);
        dest.writeList(top_stories);
        dest.writeList(storiesIds);
    }

    public static final Creator<HomeInfo> CREATOR = new Creator<HomeInfo>() {

        @Override
        public HomeInfo createFromParcel(Parcel source) {
            return new HomeInfo(source);
        }

        @Override
        public HomeInfo[] newArray(int size) {
            return new HomeInfo[size];
        }
    };

    public static class Stories implements Parcelable {
        /**
         * images : ["http://pic3.zhimg.com/afab44d4ef0758d1940eaccbe622cf9a.jpg"]
         * type : 0
         * id : 9065007
         * ga_prefix : 121415
         * title : 三国杀的战术和专利竞争
         */
        private int id;
        private int type;
        private String ga_prefix;
        private String date;
        private String title;
        private List<String> images;
        private boolean isMultiPic;
        private boolean isLoad;

        protected Stories(Parcel parcel) {
            id = parcel.readInt();
            type = parcel.readInt();
            ga_prefix = parcel.readString();
            date = parcel.readString();
            title = parcel.readString();
            images = parcel.createStringArrayList();
            isMultiPic = parcel.readByte() == 1 ? true : false;
            isLoad = parcel.readByte() == 1 ? true : false;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDate() {
            return date;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public List<String> getImages() {
            return images;
        }

        public void setMultiPic(boolean multiPic) {
            isMultiPic = multiPic;
        }

        public boolean isMultiPic() {
            return isMultiPic;
        }

        public void setLoad(boolean load) {
            isLoad = load;
        }

        public boolean isLoad() {
            return isLoad;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeInt(type);
            dest.writeString(ga_prefix);
            dest.writeString(date);
            dest.writeString(title);
            dest.writeStringList(images);
            dest.writeByte((byte) (isMultiPic ? 1 : 0));
            dest.writeByte((byte) (isLoad ? 1 : 0));
        }

        public static final Creator<Stories> CREATOR = new Creator<Stories>() {

            @Override
            public Stories createFromParcel(Parcel source) {
                return new Stories(source);
            }

            @Override
            public Stories[] newArray(int size) {
                return new Stories[size];
            }
        };
    }

    public static class TopStories implements Parcelable {
        /**
         * image : http://pic3.zhimg.com/65e1e452a62f7e279f516b654b5af3e2.jpg
         * type : 0
         * id : 9064857
         * ga_prefix : 121413
         * title : 苹果的 AirPods 开卖了，这是用了一个多月的体验
         */
        private int id;
        private int type;
        private String ga_prefix;
        private String title;
        private String image;

        protected TopStories(Parcel parcel) {
            id = parcel.readInt();
            type = parcel.readInt();
            ga_prefix = parcel.readString();
            title = parcel.readString();
            image = parcel.readString();
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImage() {
            return image;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeInt(type);
            dest.writeString(ga_prefix);
            dest.writeString(title);
            dest.writeString(image);
        }

        public static final Creator<TopStories> CREATOR = new Creator<TopStories>() {
            @Override
            public TopStories createFromParcel(Parcel source) {
                return new TopStories(source);
            }

            @Override
            public TopStories[] newArray(int size) {
                return new TopStories[size];
            }
        };
    }
}
