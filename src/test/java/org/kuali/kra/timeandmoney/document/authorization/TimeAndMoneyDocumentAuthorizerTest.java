/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.timeandmoney.document.authorization;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.fixture.KimPersonFixture;
import org.kuali.kra.bo.fixture.KimRoleFixture;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.test.infrastructure.PersonAndRoleAwareTestBase;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.service.DocumentService;

@SuppressWarnings({"deprecation"})
public class TimeAndMoneyDocumentAuthorizerTest extends PersonAndRoleAwareTestBase {
    
    private TimeAndMoneyDocument timeAndMoneyDocument;
    private DocumentService documentService;
    private TimeAndMoneyDocumentAuthorizer authorizer;
    private Person quickstart;
    private Person borst;
    private Person iacucAdmin;
    
    @Before
    public void setup() throws Exception {

        quickstart = createPerson(KimPersonFixture.QUICKSTART);
        borst = createPerson(KimPersonFixture.BORST);
        iacucAdmin = createPerson(KimPersonFixture.IACUC_ADMIN);

        addPersonToRole(quickstart, KimRoleFixture.TIME_AND_MONEY_MODIFIER);
        addPersonToRole(quickstart, KimRoleFixture.AWARD_MODIFIER);

        documentService = KraServiceLocator.getService(DocumentService.class);
        timeAndMoneyDocument = (TimeAndMoneyDocument) documentService.getNewDocument(TimeAndMoneyDocument.class);
        authorizer = new TimeAndMoneyDocumentAuthorizer();
    }
    
    @After
    public void teardown() {
        documentService = null;
        timeAndMoneyDocument = null;
        authorizer = null;
        quickstart = null;
        borst = null;
        iacucAdmin = null;
    }


    @Test
    public void testAddRoleQualificationObjectMapOfStringString() {
        Map<String, String> roleQual = new HashMap<String, String>();
        authorizer.addRoleQualification(timeAndMoneyDocument, roleQual);
        assertNotNull(roleQual.get(KcKimAttributes.UNIT_NUMBER));
    }


	@Test
    public void testCanAnnotate() {
        boolean canQuickstart = authorizer.canAnnotate(timeAndMoneyDocument, quickstart);
        assertTrue(canQuickstart);

        boolean canBorst = authorizer.canAnnotate(timeAndMoneyDocument, borst);
        assertFalse(canBorst);

        boolean canIacucAdmin = authorizer.canAnnotate(timeAndMoneyDocument, iacucAdmin);
        assertTrue(canIacucAdmin);
    }

    @Test
    public void testCanReload() {
        boolean canQuickstart = authorizer.canReload(timeAndMoneyDocument, quickstart);
        assertTrue(canQuickstart);
    }

    @Test
    public void testCanClose() {
        boolean canQuickstart = authorizer.canClose(timeAndMoneyDocument, quickstart);
        assertTrue(canQuickstart);
    }

}
