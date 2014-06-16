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
package org.kuali.kra.util.watermark;

/**
 * 
 * This class is an interface for fetching the appropriate watermark object.
 *  
 */
public interface Watermarkable {
    /**
     * 
     * This method sets the appropriate watermark 
     * bean with respect to the module.
     */
    public WatermarkBean getWatermark();
    
    /**
     * 
     * This method sets the invalid watermark bean.
     */
    public WatermarkBean getInvalidWatermark();
}