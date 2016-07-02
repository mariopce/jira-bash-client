=Usage

jira -u mario -p password -url tomtom.com getinfo --user saramakm --filter "status==inprogress"
jira -u mario -p password -url tomtom.com getinfo --user saramakm --filter "status==done"

jira -c conf.prop createTask -t "Title Of task" -d "Description of task" -p "LNS-122"
jira -c conf.prop update --task LNS-11 --addcomment "Bla bla bla" --status "In Progress"

jira -c conf.prop getinfo --task LNS-123 --filter "comments"
jira -c conf.prop getinfo --task LNS-123 --filter ""

jira -c conf.prop --team team.cvs --status
jira getinfo --user admin --filter " AND status=\"In Progress\""

jira  update --task LNS-24 --addsubtask summary=subtask1,description="fdsfs  \\n fdsfds \\n\\r vfsdsd \\fsfs In Progress"
 public static final String ASSIGNEE = "assignee";
    public static final String ATTACHMENT = "attachment";
    public static final String CHANGE_LOG = "changelog";
    public static final String CHANGE_LOG_ENTRIES = "histories";
    public static final String CHANGE_LOG_ITEMS = "items";
    public static final String COMMENT = "comment";
    public static final String COMPONENTS = "components";
    public static final String DESCRIPTION = "description";
    public static final String DUE_DATE = "duedate";
    public static final String FIX_VERSIONS = "fixVersions";
    public static final String ISSUE_LINKS = "issuelinks";
    public static final String ISSUE_TYPE = "issuetype";
    public static final String LABELS = "labels";
    public static final String PARENT = "parent";
    public static final String PRIORITY = "priority";
    public static final String PROJECT = "project";
    public static final String REPORTER = "reporter";
    public static final String RESOLUTION = "resolution";
    public static final String RESOLUTION_DATE = "resolutiondate";
    public static final String STATUS = "status";
    public static final String SUBTASKS = "subtasks";
    public static final String SUMMARY = "summary";
    public static final String TIME_TRACKING = "timetracking";
    public static final String VERSIONS = "versions";
    public static final String VOTES = "votes";
    public static final String WATCHES = "watches";
    public static final String WORKLOG = "worklog";
    public static final String TIME_ESTIMATE = "timeestimate";
    public static final String TIME_SPENT = "timespent";