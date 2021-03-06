/**
 * Copyright © 2002 Instituto Superior Técnico
 *
 * This file is part of FenixEdu Core.
 *
 * FenixEdu Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu Core.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.sourceforge.fenixedu.domain;

import net.sourceforge.fenixedu.util.Bundle;

import org.fenixedu.bennu.core.i18n.BundleUtil;

public enum ShiftType {

    TEORICA,

    PRATICA,

    TEORICO_PRATICA,

    LABORATORIAL,

    DUVIDAS,

    RESERVA,

    SEMINARY,

    PROBLEMS,

    FIELD_WORK,

    TRAINING_PERIOD,

    TUTORIAL_ORIENTATION;

    public String getSiglaTipoAula() {
        String value = this.name();
        return BundleUtil.getString(Bundle.ENUMERATION, getClass().getName() + ".short." + name());
    }

    public String getFullNameTipoAula() {
        String value = this.name();
        return BundleUtil.getString(Bundle.ENUMERATION, getClass().getName() + ".full." + name());
    }

    public String getName() {
        return name();
    }

}
