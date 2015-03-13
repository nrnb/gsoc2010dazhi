/*
 Copyright 2010 Alexander Pico
 Licensed under the Apache License, Version 2.0 (the "License"); 
 you may not use this file except in compliance with the License. 
 You may obtain a copy of the License at 
 	
 	http://www.apache.org/licenses/LICENSE-2.0 
 	
 Unless required by applicable law or agreed to in writing, software 
 distributed under the License is distributed on an "AS IS" BASIS, 
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 See the License for the specific language governing permissions and 
 limitations under the License. 
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.geo.data;

import java.util.List;

/**
 *
 * @author djiao
 */
public class GDS extends SOFT {

    private List<SOFT> subsets;
    private SOFT database;

    public SOFT getDatabase() {
        return database;
    }

    public void setDatabase(SOFT database) {
        this.database = database;
    }

    public List<SOFT> getSubsets() {
        return subsets;
    }

    public void setSubsets(List<SOFT> subsets) {
        this.subsets = subsets;
    }

    public GDS(SOFT soft) {
        super();
        this.setFields(soft.getFields());
        this.setType(Type.GDS);
        this.setId(soft.getId());
        this.setDataTables(soft.getDataTables());
    }

    @Override
    public SOFT.Type getType() {
        return Type.GDS;
    }
}
