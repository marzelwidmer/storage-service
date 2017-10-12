package ch.helsana.microservice.storageservice.resource.index;

import ch.helsana.microservice.storageservice.resource.storage.filesystem.FileSystemStorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.halLinks;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ApiController.class)
@AutoConfigureRestDocs(value = "target/generated-snippets",
        uriScheme = "https",
        uriHost = "portal.helsana.ch",
        uriPort = 443
)
public class ApiControllerDocumentation {

    private static final String SELF = "self";
    private static final String SELF_DESCRIPTION = "This resource.";

    private static final String UPLOAD = "upload";
    private static final String UPLOAD_DESCRIPTION = "Filesystem upload link.";

    private static final String STORAGE = "storage";
    private static final String STORAGE_DESCRIPTION = "Storage (DB) upload link.";

    private static final String DOCUMENTATION = "documentation";
    private static final String DOCUMENTATION_DESCRIPTION = "Documentation link.";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileSystemStorageService fileSystemStorageService;


    @Test
    public void getApi() throws Exception {
        mockMvc.perform(get("/").accept(MediaTypes.HAL_JSON))
                .andExpect(status().isOk())
                .andDo(document("{method-name}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        links(halLinks(),
                                linkWithRel(SELF).description(SELF_DESCRIPTION),
                                linkWithRel(UPLOAD).description(UPLOAD_DESCRIPTION),
                                linkWithRel(STORAGE).description(STORAGE_DESCRIPTION),
                                linkWithRel(DOCUMENTATION).description(DOCUMENTATION_DESCRIPTION)

                        )));
    }

}
