package iducs.jsp.b.kskbblog.util;

import iducs.jsp.b.kskbblog.model.Blogger;

import java.util.Comparator;

public class DescByBloggerName implements Comparator<Blogger> {

    @Override
    public int compare(Blogger o1, Blogger o2) {
        return o2.getName().compareTo(o1.getName()); // o2 <= o1 : 음수 / o2 > o1 : 1이상 내림차순
        // return o1.getContent().compareTo(o2.getTitle()); // 오름 차순
    }
}



