package com.restful.amazon;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.restful.dto.UserDto;


@Component
public class AmazonSes {

	final String awsAccessKeyId = "AKIAXA6OJQNCFGGDSUUK";
	final String awsSecretAccessKey = "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";

	final String FROM = "prasannaanubhav@gmail.com";

	final String SUBJECT = "Step To Verify The Customer";

	final String HTMLBODY = "<h1>Please verify your email address</h1>"
			+ "<p>Thank you for registering with our mobile app. To complete registration process and be able to log in,"
			+ " click on the following link: "
			+ "<a href='http://localhost:8080/Verification-token/home?token=tokenValue'>"
			+ "Final step to complete your registration" + "</a><br/><br/>"
			+ " Thank you! And we are waiting for you inside!";

	final String TEXTBODY = "Please verify your email address. "
			+ "Thank you for registering with our mobile app. To complete registration process and be able to log in,"
			+ " open then the following URL in your browser window: "
			+ " http://localhost:8080/Verification-token/home?token=tokenValue"
			+ " Thank you! And we are waiting for you inside!";

	public void verifyEmail(UserDto userDto) {

		AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.US_EAST_1)
				.withCredentials(
						new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKey)))
				.build();

		String tokenBody = HTMLBODY.replace("tokenValue", userDto.getEmailVerificationToken());
		String textTokenBody = TEXTBODY.replace("tokenValue", userDto.getEmailVerificationToken());

		SendEmailRequest request = new SendEmailRequest()
				.withDestination(new Destination().withToAddresses(userDto.getEmail()))
				.withMessage(new Message()
						.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(tokenBody))
								.withText(new Content().withCharset("UTF-8").withData(textTokenBody)))
						.withSubject(new Content().withCharset("UTF-8").withData(SUBJECT)))
				.withSource(FROM);
		client.sendEmail(request);

		System.out.println("Email Verification sent!");

	}

	public void sendEmailForPasswordReset(UserDto userDto, String token) {

		final String SUBJECT = "Step To Verify The Password";

		final String HTMLBODY = "<h1>Please verify your Password</h1>"
				+ "<p>Thank you for registering with our mobile app. To complete reset process and be able to log in,"
				+ " click on the following link: "
				+ "<a href='http://localhost:8080/Verification-token/password?token=tokenValue'>"
				+ "Final step to complete your Reset Password" + "</a><br/><br/>"
				+ " Thank you! And we are waiting for you inside!";

		final String TEXTBODY = "Please verify your Password. "
				+ "Thank you for registering with our mobile app. To complete reset process and be able to log in,"
				+ " open then the following URL in your browser window: "
				+ " http://localhost:8080/Verification-token/password?token=tokenValue"
				+ " Thank you! And we are waiting for you inside!";

		AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.US_EAST_1)
				.withCredentials(
						new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKey)))
				.build();

		String PasswordtokenBody = HTMLBODY.replace("tokenValue", token);
		String PasswordtextTokenBody = TEXTBODY.replace("tokenValue", token);

		SendEmailRequest request = new SendEmailRequest()
				.withDestination(new Destination().withToAddresses(userDto.getEmail()))
				.withMessage(new Message()
						.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(PasswordtokenBody))
								.withText(new Content().withCharset("UTF-8").withData(PasswordtextTokenBody)))
						.withSubject(new Content().withCharset("UTF-8").withData(SUBJECT)))
				.withSource(FROM);

		client.sendEmail(request);

		System.out.println("Password Verification sent!");

	}

}
