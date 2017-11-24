package com.github.ksokol.mailsink.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContentAssert;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Kamill Sokol
 */
@RunWith(SpringRunner.class)
@JsonTest
public class MailAttachmentJsonTest {

    @Autowired
    private JacksonTester<MailAttachment> json;

    private JsonContentAssert jsonContentAssert;

    @Before
    public void setUp() throws Exception {
        MailAttachment attachment = new MailAttachment();
        attachment.setId(2L);
        attachment.setFilename("expected filename");
        attachment.setMimeType("expected mimeType");
        attachment.setDispositionType("expected dispositionType");
        attachment.setData(new byte[] {97});

        jsonContentAssert = assertThat(json.write(attachment));
    }

    @Test
    public void shouldSerializeFields() throws Exception {
        jsonContentAssert.extractingJsonPathValue("filename").isEqualTo("expected filename");
        jsonContentAssert.extractingJsonPathValue("mimeType").isEqualTo("expected mimeType");
        jsonContentAssert.extractingJsonPathValue("dispositionType").isEqualTo("expected dispositionType");
    }

    @Test
    public void shouldNotSerializeFields() throws Exception {
        jsonContentAssert.doesNotHaveJsonPathValue("id");
        jsonContentAssert.doesNotHaveJsonPathValue("mail");
        jsonContentAssert.doesNotHaveJsonPathValue("data");
    }
}
