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
package org.pentaho.platform.repository2.unified.importexport;

import java.io.InputStream;

import org.pentaho.platform.api.repository2.unified.IRepositoryFileData;

/**
 * Implementations of this interface are responsible for converting between an {@code InputStream} and an 
 * {@link IRepositoryFileData}.
 * 
 * @author mlowery
 */
public interface Converter {

  IRepositoryFileData convert(final InputStream inputStream, final String charset, final String mimeType);

  InputStream convert(final IRepositoryFileData data);

}