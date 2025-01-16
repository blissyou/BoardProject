package org.example.board.post.db;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.board.post.model.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.example.board.post.db.QPostEntity.postEntity;

@RequiredArgsConstructor
@Repository
public class BoardRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public Page<PostEntity> finAllBySearchCondition(Pageable pageable, SearchCondition searchCondition){
        JPAQuery<PostEntity> query = queryFactory.selectFrom(postEntity)
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()));

        long total = query.stream().count();

        List<PostEntity>postEntityList = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(postEntityList,pageable,total);
    }

    private BooleanExpression searchKeywords(String sk,String sv){
        if("title".equals(sk) && StringUtils.hasLength(sv)){
            return postEntity.title.contains(sv);

        } else if("userName".equals(sk) && StringUtils.hasLength(sv)){
                return postEntity.userName.contains(sv);

        }else if("content".equals(sk) && StringUtils.hasLength(sv)){
                return postEntity.content.contains(sv);

        }
        return postEntity.isNotNull();
    }
}
