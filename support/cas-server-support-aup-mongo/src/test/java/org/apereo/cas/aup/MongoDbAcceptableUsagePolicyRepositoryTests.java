package org.apereo.cas.aup;

import org.apereo.cas.config.CasAcceptableUsagePolicyMongoDbConfiguration;
import org.apereo.cas.util.CollectionUtils;
import org.apereo.cas.util.junit.EnabledIfPortOpen;

import lombok.Getter;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is {@link MongoDbAcceptableUsagePolicyRepositoryTests}.
 *
 * @author Misagh Moayyed
 * @since 5.3.0
 */
@Tag("MongoDb")
@Import(CasAcceptableUsagePolicyMongoDbConfiguration.class)
@EnabledIfPortOpen(port = 27017)
@TestPropertySource(properties = {
    "cas.acceptableUsagePolicy.mongo.host=localhost",
    "cas.acceptableUsagePolicy.mongo.port=27017",
    "cas.acceptableUsagePolicy.mongo.dropCollection=true",
    "cas.acceptableUsagePolicy.mongo.collection=acceptable-usage-policy",
    "cas.acceptableUsagePolicy.mongo.userId=root",
    "cas.acceptableUsagePolicy.mongo.password=secret",
    "cas.acceptableUsagePolicy.mongo.databaseName=acceptableUsagePolicy",
    "cas.acceptableUsagePolicy.mongo.authenticationDatabaseName=admin",
    "cas.acceptableUsagePolicy.aupAttributeName=accepted"
})
@Getter
public class MongoDbAcceptableUsagePolicyRepositoryTests extends BaseAcceptableUsagePolicyRepositoryTests {

    @Autowired
    @Qualifier("acceptableUsagePolicyRepository")
    protected AcceptableUsagePolicyRepository acceptableUsagePolicyRepository;

    @Test
    public void verifyOperation() {
        assertNotNull(acceptableUsagePolicyRepository);
        verifyRepositoryAction("casuser",
            CollectionUtils.wrap("accepted", List.of("false"), "email", List.of("CASuser@example.org")));
    }
}
