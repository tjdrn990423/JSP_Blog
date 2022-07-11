package iducs.jsp.b.kskbblog.repository;

import iducs.jsp.b.kskbblog.model.Blogger;
import iducs.jsp.b.kskbblog.util.Pagination;
import java.util.List;

public interface BloggerDAO {
    int create(Blogger blogger);
    Blogger read(Blogger blogger);
    Blogger readBlogger(Blogger blogger);
    List<Blogger> readList();
    int update(Blogger blogger);
    int delete(Blogger blogger);

    int readTotalRows();
    List<Blogger> readListPagination(Pagination pagination);
}
