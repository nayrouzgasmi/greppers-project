package tn.esprit.pidev.Util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class ObjectStorage {
  static AWSCredentials credentials = new BasicAWSCredentials(
      "DO00VLM3WGWHRR3LA2U6",
      "dSkgpu6iu1BhAM+xewcIto76Javivh22PomtXr8FQlQ");
      public static AmazonS3 s3client = AmazonS3ClientBuilder
  .standard()
  .withCredentials(new AWSStaticCredentialsProvider(credentials))
  .withRegion(Regions.EU_CENTRAL_1)
  .build();

}
