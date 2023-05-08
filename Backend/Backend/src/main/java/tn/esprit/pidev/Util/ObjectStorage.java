package tn.esprit.pidev.Util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.costandusagereport.model.AWSRegion;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class ObjectStorage {

	// @Value("DO00VLM3WGWHRR3LA2U6")
	private String doSpaceKey="DO00VLM3WGWHRR3LA2U6";

	// @Value("dSkgpu6iu1BhAM+xewcIto76Javivh22PomtXr8FQlQ")
	private String doSpaceSecret="dSkgpu6iu1BhAM+xewcIto76Javivh22PomtXr8FQlQ";

	// @Value("${do.space.endpoint}")
	private String doSpaceEndpoint="https://fra1.digitaloceanspaces.com";

	// @Value("${do.space.region}")
	private String doSpaceRegion="fra1";

	@Bean
	public AmazonS3 getS3() {
		BasicAWSCredentials creds = new BasicAWSCredentials("DO00VLM3WGWHRR3LA2U6", "dSkgpu6iu1BhAM+xewcIto76Javivh22PomtXr8FQlQ");
		return AmazonS3ClientBuilder.standard()
				.withEndpointConfiguration(new EndpointConfiguration(doSpaceEndpoint, "frankfurt-1"))
				.withCredentials(new AWSStaticCredentialsProvider(creds)).build();
	}

}
// "DO00VLM3WGWHRR3LA2U6",
// "dSkgpu6iu1BhAM+xewcIto76Javivh22PomtXr8FQlQ");