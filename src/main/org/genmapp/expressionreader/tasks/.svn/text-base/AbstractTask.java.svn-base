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

package org.genmapp.expressionreader.tasks;

import cytoscape.task.Task;
import cytoscape.task.TaskMonitor;

/**
 *
 * @author djiao
 */
public abstract class AbstractTask implements Task {

    protected TaskMonitor taskMonitor = null;

    public void halt() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTaskMonitor(TaskMonitor tm) throws IllegalThreadStateException {
        if (this.taskMonitor != null) {
            throw new IllegalStateException("Task Monitor is already set.");
        }
        this.taskMonitor = tm;
    }
}
