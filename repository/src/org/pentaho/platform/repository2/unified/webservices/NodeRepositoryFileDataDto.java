/*
 * This program is free software; you can redistribute it and/or modify it under the 
 * terms of the GNU General Public License, version 2 as published by the Free Software 
 * Foundation.
 *
 * You should have received a copy of the GNU General Public License along with this 
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/gpl-2.0.html 
 * or from the Free Software Foundation, Inc., 
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */
package org.pentaho.platform.repository2.unified.webservices;

import java.io.Serializable;

public class NodeRepositoryFileDataDto implements Serializable {
  public NodeRepositoryFileDataDto() {
    super();
    // TODO Auto-generated constructor stub
  }

  DataNodeDto node;

  public DataNodeDto getNode() {
    return node;
  }

  public void setNode(DataNodeDto node) {
    this.node = node;
  }

  @SuppressWarnings("nls")
  @Override
  public String toString() {
    return "NodeRepositoryFileDataDto [node=" + node + "]";
  }
}