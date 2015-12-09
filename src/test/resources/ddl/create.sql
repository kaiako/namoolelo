CREATE TABLE
    account
    (
        id bigint NOT NULL AUTO_INCREMENT,
        email VARCHAR(255),
        password VARCHAR(255),
        username VARCHAR(255),
        PRIMARY KEY (id),
        CONSTRAINT UK_q0uja26qgu1atulenwup9rxyr UNIQUE (email),
        CONSTRAINT UK_gex1lmaqpg0ir5g1f5eftyaa1 UNIQUE (username)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
CREATE TABLE
    actor
    (
        id bigint NOT NULL AUTO_INCREMENT,
        description VARCHAR(255),
        name VARCHAR(255),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE
    moolelo
    (
        id bigint NOT NULL AUTO_INCREMENT,
        est_date VARCHAR(255),
        summary VARCHAR(255),
        text VARCHAR(255),
        title VARCHAR(255),
        owner_id bigint,
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE
    moolelo_actor
    (
        moolelo_id bigint NOT NULL,
        actor_id bigint NOT NULL,
        PRIMARY KEY (moolelo_id, actor_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE
    person
    (
        id bigint NOT NULL AUTO_INCREMENT,
        first_name VARCHAR(255),
        last_name VARCHAR(255),
        middle_name VARCHAR(255),
        suffix VARCHAR(255),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
CREATE TABLE
    place
    (
        id bigint NOT NULL AUTO_INCREMENT,
        island VARCHAR(255),
        latitude FLOAT NOT NULL,
        longitude FLOAT NOT NULL,
        moku VARCHAR(255),
        name VARCHAR(255),
        moolelo_id bigint,
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;

