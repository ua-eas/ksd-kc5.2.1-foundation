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
package org.kuali.kra.iacuc.committee.service.impl;

import org.kuali.kra.common.committee.meeting.CommitteeScheduleAttachmentsBase;
import org.kuali.kra.common.committee.service.impl.CommitteeScheduleServiceImplBase;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule;
import org.kuali.kra.iacuc.committee.meeting.IacucCommitteeScheduleAttachments;
import org.kuali.kra.iacuc.committee.meeting.IacucCommitteeScheduleMinute;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeScheduleService;

public class IacucCommitteeScheduleServiceImpl extends CommitteeScheduleServiceImplBase<IacucCommitteeSchedule, IacucCommittee, IacucCommitteeScheduleMinute> implements IacucCommitteeScheduleService{

    @Override
    protected IacucCommitteeSchedule getNewCommiteeScheduleInstanceHook() {
        return new IacucCommitteeSchedule(); 
    }

    @Override
    protected Class<IacucCommitteeScheduleMinute> getCommitteeScheduleMinuteBOClassHook() {
        return IacucCommitteeScheduleMinute.class;
    }

    @Override
    protected CommitteeScheduleAttachmentsBase getNewCommitteeScheduleAttachmentsInstanceHook() {
        return new IacucCommitteeScheduleAttachments();
    }

}
