package org.kuali.kra.test.helpers;

import org.kuali.kra.bo.NoticeOfOpportunity;
import org.kuali.kra.test.fixtures.NoticeOfOpportunityFixture;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;

public class NoticeOfOpportunityTestHelper extends TestHelper {

	public void createNoticeOfOpportunity( NoticeOfOpportunityFixture notice ) {
		BusinessObjectService businessObjectService = getService( BusinessObjectService.class );
		NoticeOfOpportunity noticeOfOpportunity = buildNoticeOfOpportunity( notice );
		businessObjectService.save( noticeOfOpportunity );
	}

	public void deleteNoticeOfOpportunity( NoticeOfOpportunityFixture notice ) {
		BusinessObjectService businessObjectService = getService( BusinessObjectService.class );
		NoticeOfOpportunity noticeOfOpportunity = buildNoticeOfOpportunity( notice );
		businessObjectService.save( noticeOfOpportunity );
	}

	public KeyValue getNoticeKeyValue( NoticeOfOpportunityFixture notice ) {
		return new ConcreteKeyValue( notice.getKey(), notice.getValue() );
	}

	public NoticeOfOpportunity buildNoticeOfOpportunity( NoticeOfOpportunityFixture notice ) {
		NoticeOfOpportunity noticeOfOpportunity = new NoticeOfOpportunity();
		noticeOfOpportunity.setNoticeOfOpportunityCode( notice.getKey() );
		noticeOfOpportunity.setDescription( notice.getValue() );
		return noticeOfOpportunity;
	}

}
