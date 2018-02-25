package online.dinghuiye.bingcollection.entity;

/**
 * bing接口获取的json数据封装对象
 *
 * @author Strangeen on 2018/01/09
 */
public class BingImageUrlWrapper {

    private Image[] images;
    private ToolTips tooltips;

    public Image[] getImages() {
        return images;
    }

    public void setImages(Image[] images) {
        this.images = images;
    }

    public ToolTips getTooltips() {
        return tooltips;
    }

    public void setTooltips(ToolTips tooltips) {
        this.tooltips = tooltips;
    }

    public static class Image {
        private String startdate;
        private String fullstartdate;
        private String enddate;
        private String url;
        private String urlbase;
        private String copyright;
        private String copyrightlink;
        private String quiz;
        private String wp;
        private String hsh;
        private String drk;
        private String top;
        private String bot;
        private String hs;
        private VideoUrlWrapper vid;

        public String getStartdate() {
            return startdate;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }

        public String getFullstartdate() {
            return fullstartdate;
        }

        public void setFullstartdate(String fullstartdate) {
            this.fullstartdate = fullstartdate;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlbase() {
            return urlbase;
        }

        public void setUrlbase(String urlbase) {
            this.urlbase = urlbase;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public String getCopyrightlink() {
            return copyrightlink;
        }

        public void setCopyrightlink(String copyrightlink) {
            this.copyrightlink = copyrightlink;
        }

        public String getQuiz() {
            return quiz;
        }

        public void setQuiz(String quiz) {
            this.quiz = quiz;
        }

        public String getWp() {
            return wp;
        }

        public void setWp(String wp) {
            this.wp = wp;
        }

        public String getHsh() {
            return hsh;
        }

        public void setHsh(String hsh) {
            this.hsh = hsh;
        }

        public String getDrk() {
            return drk;
        }

        public void setDrk(String drk) {
            this.drk = drk;
        }

        public String getTop() {
            return top;
        }

        public void setTop(String top) {
            this.top = top;
        }

        public String getBot() {
            return bot;
        }

        public void setBot(String bot) {
            this.bot = bot;
        }

        public String getHs() {
            return hs;
        }

        public void setHs(String hs) {
            this.hs = hs;
        }

        public VideoUrlWrapper getVid() {
            return vid;
        }

        public void setVid(VideoUrlWrapper vid) {
            this.vid = vid;
        }
    }

    public static class VideoUrlWrapper {
        private String[][] sources;
        private String loop;
        private String image;
        private String mobileimage;
        private String caption;
        private String captionlink;
        private String dark;
        private String audiopresent;

        public String[][] getSources() {
            return sources;
        }

        public void setSources(String[][] sources) {
            this.sources = sources;
        }

        public String getLoop() {
            return loop;
        }

        public void setLoop(String loop) {
            this.loop = loop;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getMobileimage() {
            return mobileimage;
        }

        public void setMobileimage(String mobileimage) {
            this.mobileimage = mobileimage;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public String getCaptionlink() {
            return captionlink;
        }

        public void setCaptionlink(String captionlink) {
            this.captionlink = captionlink;
        }

        public String getDark() {
            return dark;
        }

        public void setDark(String dark) {
            this.dark = dark;
        }

        public String getAudiopresent() {
            return audiopresent;
        }

        public void setAudiopresent(String audiopresent) {
            this.audiopresent = audiopresent;
        }
    }

    public static class ToolTips {
        private String loading;
        private String previous;
        private String next;
        private String walle;
        private String walls;
        private String play;
        private String pause;

        public String getLoading() {
            return loading;
        }

        public void setLoading(String loading) {
            this.loading = loading;
        }

        public String getPrevious() {
            return previous;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getWalle() {
            return walle;
        }

        public void setWalle(String walle) {
            this.walle = walle;
        }

        public String getWalls() {
            return walls;
        }

        public void setWalls(String walls) {
            this.walls = walls;
        }

        public String getPlay() {
            return play;
        }

        public void setPlay(String play) {
            this.play = play;
        }

        public String getPause() {
            return pause;
        }

        public void setPause(String pause) {
            this.pause = pause;
        }
    }

}
