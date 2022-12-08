/*
 * (C) Copyright 2015 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Thijs Vonk
 */
package org.nuxeo.tools.gatling.report;

import java.io.File;
import java.util.*;

/**q
 * Gatling 3.5 simulation format
 */
public class SimulationParserV36 extends SimulationParser {

    final protected Set<String> scenario = new HashSet<>();

    public SimulationParserV36(File file, Float apdexT) {
        super(file, apdexT);
    }

    public SimulationParserV36(File file) {
        super(file);
    }

    protected String getSimulationName(List<String> line) {
        return line.get(2);
    }

    protected String getSimulationStart(List<String> line) {
        return line.get(3);
    }

    protected String getScenario(List<String> line) {
        String user;
        if (USER.equals(line.get(0))) {
            user = line.get(2);
            if (START.equals(line.get(2))) {
                scenario.add(line.get(1));
                return line.get(1);
            }
        } else if (RUN.equals(line.get(0))) {
            return line.get(1);
        } else {
            user = line.get(1);
        }
        return scenario.stream().filter($ -> $.contains(user)).findFirst().orElse(null);
    }

    protected String getType(List<String> line) {
        return line.get(0);
    }

    protected String getUserType(List<String> line) {
        return line.get(2);
    }

    protected String getRequestName(List<String> line) {
        return line.get(2);
    }

    protected Long getRequestStart(List<String> line) {
        return Long.parseLong(line.get(3));
    }

    protected Long getRequestEnd(List<String> line) {
        return Long.parseLong(line.get(4));
    }

    protected boolean getRequestSuccess(List<String> line) {
        return OK.equals(line.get(5));
    }
}
