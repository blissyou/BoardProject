package org.example.board.reply.db;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReplyEntity is a Querydsl query type for ReplyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReplyEntity extends EntityPathBase<ReplyEntity> {

    private static final long serialVersionUID = 1957425591L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReplyEntity replyEntity = new QReplyEntity("replyEntity");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final org.example.board.post.db.QPostEntity post;

    public final DateTimePath<java.time.LocalDateTime> repliedAt = createDateTime("repliedAt", java.time.LocalDateTime.class);

    public final StringPath status = createString("status");

    public final StringPath title = createString("title");

    public final StringPath userName = createString("userName");

    public QReplyEntity(String variable) {
        this(ReplyEntity.class, forVariable(variable), INITS);
    }

    public QReplyEntity(Path<? extends ReplyEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReplyEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReplyEntity(PathMetadata metadata, PathInits inits) {
        this(ReplyEntity.class, metadata, inits);
    }

    public QReplyEntity(Class<? extends ReplyEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new org.example.board.post.db.QPostEntity(forProperty("post"), inits.get("post")) : null;
    }

}

