package com.programming.techie.pdfassistant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.chain.ConversationalRetrievalChain;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.huggingface.HuggingFaceChatModel;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.neo4j.Neo4jEmbeddingStore;

@Configuration
public class PdfAssistantConfig {
	@Bean
	public EmbeddingModel embeddingModel() {
		return new AllMiniLmL6V2EmbeddingModel();
	}

	@Bean
	public Neo4jEmbeddingStore neo4jEmbeddingStore() {

		
		return Neo4jEmbeddingStore.builder()
	    .withBasicAuth("bolt://localhost:7687", "admin", "admin123")
	    .awaitIndexTimeout(60000)
	    .databaseName("pratik")
	    .dimension(384)
	    .build();
	}

	@Bean
	public EmbeddingStoreIngestor embeddingStoreIngestor() {
		return EmbeddingStoreIngestor.builder()
				.documentSplitter(DocumentSplitters.recursive(300, 0))
				.embeddingModel(embeddingModel())
				.embeddingStore(neo4jEmbeddingStore())
				.build();
	}

	@Bean
	public ConversationalRetrievalChain conversationalRetrievalChain() {
		return ConversationalRetrievalChain.builder()
				.chatLanguageModel(HuggingFaceChatModel.withAccessToken("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"))
				.retriever(EmbeddingStoreRetriever.from(neo4jEmbeddingStore(), embeddingModel()))
				.build();
	}
}
