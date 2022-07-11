package iducs.jsp.b.kskbblog.repository;

import iducs.jsp.b.kskbblog.model.Blog;
import iducs.jsp.b.kskbblog.util.Pagination;

import java.util.List;

// crud : create read update delete
// http method : post, get, put, delete
public interface BlogDAO {
        int create(Blog blog);
        Blog read(Blog blog);
        List<Blog> readList();
        int update(Blog blog);
        int delete(Blog blog);

        int readTotalRows();
        List<Blog> readListPagination(Pagination pagination);
}
