package org.example.board.board.db;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardEntity is a Querydsl query type for BoardEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardEntity extends EntityPathBase<BoardEntity> {

    private static final long serialVersionUID = -2036134601L;

    public static final QBoardEntity boardEntity = new QBoardEntity("boardEntity");

    public final StringPath boardName = createString("boardName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<org.example.board.post.db.PostEntity, org.example.board.post.db.QPostEntity> postlist = this.<org.example.board.post.db.PostEntity, org.example.board.post.db.QPostEntity>createList("postlist", org.example.board.post.db.PostEntity.class, org.example.board.post.db.QPostEntity.class, PathInits.DIRECT2);

    public final StringPath status = createString("status");

    public QBoardEntity(String variable) {
        super(BoardEntity.class, forVariable(variable));
    }

    public QBoardEntity(Path<? extends BoardEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardEntity(PathMetadata metadata) {
        super(BoardEntity.class, metadata);
    }

}

