package com.practiceapps.donal.mybluekins.POJO;

/**
 * Created by donal on 26/02/2015.
 */
public class BuildSummary
{
    private Jobs[] jobs;

    private String[] assignedLabels;

    private String overallLoad;

    private String nodeName;

    private String mode;

    private String unlabeledLoad;

    private String nodeDescription;

    private PrimaryView primaryView;

    private String quietingDown;

    private String slaveAgentPort;

    private Views[] views;

    private String numExecutors;

    private String useCrumbs;

    private String useSecurity;

    public Jobs[] getJobs ()
    {
        return jobs;
    }

    public void setJobs (Jobs[] jobs)
    {
        this.jobs = jobs;
    }

    public String[] getAssignedLabels ()
    {
        return assignedLabels;
    }

    public void setAssignedLabels (String[] assignedLabels)
    {
        this.assignedLabels = assignedLabels;
    }

    public String getOverallLoad ()
    {
        return overallLoad;
    }

    public void setOverallLoad (String overallLoad)
    {
        this.overallLoad = overallLoad;
    }

    public String getNodeName ()
    {
        return nodeName;
    }

    public void setNodeName (String nodeName)
    {
        this.nodeName = nodeName;
    }

    public String getMode ()
    {
        return mode;
    }

    public void setMode (String mode)
    {
        this.mode = mode;
    }

    public String getUnlabeledLoad ()
    {
        return unlabeledLoad;
    }

    public void setUnlabeledLoad (String unlabeledLoad)
    {
        this.unlabeledLoad = unlabeledLoad;
    }

    public String getNodeDescription ()
    {
        return nodeDescription;
    }

    public void setNodeDescription (String nodeDescription)
    {
        this.nodeDescription = nodeDescription;
    }

    public PrimaryView getPrimaryView ()
    {
        return primaryView;
    }

    public void setPrimaryView (PrimaryView primaryView)
    {
        this.primaryView = primaryView;
    }

    public String getQuietingDown ()
    {
        return quietingDown;
    }

    public void setQuietingDown (String quietingDown)
    {
        this.quietingDown = quietingDown;
    }

    public String getSlaveAgentPort ()
    {
        return slaveAgentPort;
    }

    public void setSlaveAgentPort (String slaveAgentPort)
    {
        this.slaveAgentPort = slaveAgentPort;
    }

    public Views[] getViews ()
    {
        return views;
    }

    public void setViews (Views[] views)
    {
        this.views = views;
    }


    public String getNumExecutors ()
    {
        return numExecutors;
    }

    public void setNumExecutors (String numExecutors)
    {
        this.numExecutors = numExecutors;
    }

    public String getUseCrumbs ()
    {
        return useCrumbs;
    }

    public void setUseCrumbs (String useCrumbs)
    {
        this.useCrumbs = useCrumbs;
    }

    public String getUseSecurity ()
    {
        return useSecurity;
    }

    public void setUseSecurity (String useSecurity)
    {
        this.useSecurity = useSecurity;
    }
}

