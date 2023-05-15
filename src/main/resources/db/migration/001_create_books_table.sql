CREATE TABLE books (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  author VARCHAR(255) NOT NULL,
  isbn VARCHAR(255) NOT NULL,
  added_date DATE NOT NULL,
  deleted_date DATE,
  plot TEXT,
  num_reads INTEGER CHECK (num_reads >= 0)
);
