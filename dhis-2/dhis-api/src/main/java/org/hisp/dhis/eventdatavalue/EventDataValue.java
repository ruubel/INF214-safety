package org.hisp.dhis.eventdatavalue;
/*
 * Copyright (c) 2004-2018, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.hisp.dhis.common.DxfNamespaces;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author David Katuscak
 */
@JacksonXmlRootElement( localName = "eventDataValue", namespace = DxfNamespaces.DXF_2_0 )
public class EventDataValue implements Serializable
{
    /**
     * Determines if a de-serialized file is compatible with this class.
     */
    private static final long serialVersionUID = 2738519623273453182L;

//    private DataElement dataElement;
    private String dataElement;

    //TODO: Maybe I need to keep at least an UID of programStageInstance so I can use it in equals and
    //TODO: hashCode (for uses in collections / hibernate cache)?
//    private ProgramStageInstance programStageInstance;

    private Date created = new Date();

    private Date lastUpdated = new Date();

    private String value;

    private Boolean providedElsewhere = false;

    private String storedBy;

    // -------------------------------------------------------------------------
    // Transient properties
    // -------------------------------------------------------------------------

    private transient boolean auditValueIsSet = false;

    private transient boolean valueIsSet = false;

    private transient String auditValue;

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    public EventDataValue()
    {

    }

    public EventDataValue( String dataElement, String value )
    {
        this.dataElement = dataElement;
//        this.programStageInstance = programStageInstance;
        setValue( value );
    }

    public void setAutoFields()
    {
        Date date = new Date();

        if ( created == null )
        {
            created = date;
        }

        setLastUpdated( date );
    }

    // -------------------------------------------------------------------------
    // hashCode, equals and toString
    // -------------------------------------------------------------------------

    @Override
    public int hashCode()
    {
        return 31 + (StringUtils.isEmpty(dataElement ) ? 0 : dataElement.hashCode());
    }

    @Override
    public boolean equals( Object object )
    {
        if ( object == null ) {
            return false;
        }
        else if ( this == object ) {
            return true;
        }

        if ( !( object instanceof EventDataValue ) ) {
            return false;
        }

        //TODO: Maybe I need to keep at least an UID of programStageInstance so I can use it in equals and
        //TODO: hashCode (for uses in collections / hibernate cache)?

        return dataElement.equals( ( (EventDataValue) object ).dataElement );
    }

    // -------------------------------------------------------------------------
    // Getters and setters
    // -------------------------------------------------------------------------

//    public void setProgramStageInstance( ProgramStageInstance programStageInstance )
//    {
//        this.programStageInstance = programStageInstance;
//    }
//
//    public ProgramStageInstance getProgramStageInstance()
//    {
//        return programStageInstance;
//    }

    public Boolean getProvidedElsewhere()
    {
        return providedElsewhere;
    }

    public void setProvidedElsewhere( Boolean providedElsewhere )
    {
        this.providedElsewhere = providedElsewhere;
    }

    public void setDataElement( String dataElement )
    {
        this.dataElement = dataElement;
    }

    public String getDataElement()
    {
        return dataElement;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated( Date created )
    {
        this.created = created;
    }

    public Date getLastUpdated()
    {
        return lastUpdated;
    }

    public void setLastUpdated( Date lastUpdated )
    {
        this.lastUpdated = lastUpdated;
    }

    public void setValue( String value )
    {
        if ( !auditValueIsSet )
        {
            auditValue = valueIsSet ? this.value : value;
            auditValueIsSet = true;
        }

        valueIsSet = true;

        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public String getStoredBy()
    {
        return storedBy;
    }

    public void setStoredBy( String storedBy )
    {
        this.storedBy = storedBy;
    }

    public String getAuditValue()
    {
        return auditValue;
    }

    @Override public String toString()
    {
        return "EventDataValue{" +
            "dataElement=" + dataElement +
//            ", programStageInstance=" + programStageInstance.getUid() +
            ", created=" + created +
            ", lastUpdated=" + lastUpdated +
            ", value='" + value + '\'' +
            ", providedElsewhere=" + providedElsewhere +
            ", storedBy='" + storedBy + '\'' +
            '}';
    }
}
