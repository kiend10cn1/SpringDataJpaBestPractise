package com.bookstore.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

public class QAuthor extends EntityPathBase<Author> {
    private static final long serialVersionUID = -953127725L;
    public static final QAuthor author = new QAuthor("author");
    public final NumberPath<Integer> age = this.createNumber("age", Integer.class);
    public final StringPath genre = this.createString("genre");
    public final NumberPath<Long> id = this.createNumber("id", Long.class);
    public final StringPath name = this.createString("name");

    public QAuthor(String variable) {
        super(Author.class, PathMetadataFactory.forVariable(variable));
    }

    public QAuthor(Path<? extends Author> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthor(PathMetadata metadata) {
        super(Author.class, metadata);
    }
}
