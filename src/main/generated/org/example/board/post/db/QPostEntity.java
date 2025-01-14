package org.example.board.post.db;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostEntity is a Querydsl query type for PostEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostEntity extends EntityPathBase<PostEntity> {

    private static final long serialVersionUID = -1349988037L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostEntity postEntity = new QPostEntity("postEntity");

    public final org.example.board.board.db.QBoardEntity board;

    public final StringPath content = createString("content");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final DateTimePath<java.time.LocalDateTime> postedAt = createDateTime("postedAt", java.time.LocalDateTime.class);

    public final ListPath<org.example.board.reply.db.ReplyEntity, org.example.board.reply.db.QReplyEntity> replylist = this.<org.example.board.reply.db.ReplyEntity, org.example.board.reply.db.QReplyEntity>createList("replylist", org.example.board.reply.db.ReplyEntity.class, org.example.board.reply.db.QReplyEntity.class, PathInits.DIRECT2);

    public final StringPath status = createString("status");

    public final StringPath title = createString("title");

    public final StringPath userName = createString("userName");

    public QPostEntity(String variable) {
        this(PostEntity.class, forVariable(variable), INITS);
    }

    public QPostEntity(Path<? extends PostEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostEntity(PathMetadata metadata, PathInits inits) {
        this(PostEntity.class, metadata, inits);
    }

    public QPostEntity(Class<? extends PostEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new org.example.board.board.db.QBoardEntity(forProperty("board")) : null;
    }

}

