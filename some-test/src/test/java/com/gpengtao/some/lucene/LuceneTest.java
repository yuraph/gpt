/*
 * Copyright (c) 2018 Wormpex.com. All Rights Reserved.
 */
package com.gpengtao.some.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.StandardDirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

/**
 * @author pengtao.geng on 2018/6/9 下午8:02.
 */
public class LuceneTest {


	@Test
	public void test() throws IOException, URISyntaxException {

		FSDirectory directory = FSDirectory.open(Paths.get(new URI("file:///Users/gpengtao/gpt_lucene")));

		IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig());

		Field field = new TextField("gpt", "hello world", Field.Store.YES);
		Document doc = new Document();
		doc.add(field);

		indexWriter.addDocument(doc);

		indexWriter.commit();

		indexWriter.close();
	}

	@Test
	public void test_search() throws URISyntaxException, IOException {
		FSDirectory directory = FSDirectory.open(Paths.get(new URI("file:///Users/gpengtao/gpt_lucene")));

		IndexSearcher searcher = new IndexSearcher(StandardDirectoryReader.open(directory));
		TopDocs docs = searcher.search(new TermQuery(new Term("gpt", "hello")), 100);

		System.out.println(docs.totalHits);

		for (ScoreDoc doc : docs.scoreDocs) {
			System.out.println(doc);
		}

	}
}
