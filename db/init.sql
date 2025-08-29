-- Sequences
CREATE SEQUENCE IF NOT EXISTS auction_id_seq START 1;
CREATE SEQUENCE IF NOT EXISTS bid_id_seq START 1;
CREATE SEQUENCE IF NOT EXISTS watchlist_item_id_seq START 1;
CREATE SEQUENCE IF NOT EXISTS user_id_seq START 1;

-- Tables
CREATE TABLE IF NOT EXISTS auctions (
    id BIGINT PRIMARY KEY DEFAULT nextval('auction_id_seq'),
    title VARCHAR(255),
    description VARCHAR(1000),
    current_bid NUMERIC(19,2),
    minimum_bid NUMERIC(19,2),
    image_url VARCHAR(255),
    category VARCHAR(255),
    organic BOOLEAN,
    weight VARCHAR(255),
    location VARCHAR(255),
    end_time TIMESTAMP,
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS bids (
    id BIGINT PRIMARY KEY DEFAULT nextval('bid_id_seq'),
    auction_id BIGINT REFERENCES auctions(id) ON DELETE CASCADE,
    amount NUMERIC(19,2),
    bidder_name VARCHAR(255),
    timestamp TIMESTAMP
);

CREATE TABLE IF NOT EXISTS categories (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    icon VARCHAR(255),
    active_auctions INT
);

CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY DEFAULT nextval('user_id_seq'),
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS watchlist_items (
    id BIGINT PRIMARY KEY DEFAULT nextval('watchlist_item_id_seq'),
    user_id BIGINT,
    auction_id BIGINT REFERENCES auctions(id) ON DELETE CASCADE,
    added_at TIMESTAMP
);

-- Indexes
CREATE INDEX IF NOT EXISTS idx_auctions_category ON auctions(category);
CREATE INDEX IF NOT EXISTS idx_bids_auction_id ON bids(auction_id);
CREATE INDEX IF NOT EXISTS idx_watchlist_user ON watchlist_items(user_id);
CREATE INDEX IF NOT EXISTS idx_watchlist_auction ON watchlist_items(auction_id);
