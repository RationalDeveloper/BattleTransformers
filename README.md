# BattleTransformers:
Transformers: An Epic Battle between the humanoid Robots - Heroic Autobots and Evil Decepticons<br>
Spring Boot Web(Extjs 6) with in-mem H2 database, REST endpoints to create, update, delete a Transfomer, List all the transformers, and battle between the two teams (based on several basic and special rules).<br>
For more details on the Transformer's Battle rules and properties please refer to ProblemStatement.txt

# How to Use:
Prereq : Java 1.8+, Maven,  git clone https://github.com/RationalDeveloper/BattleTransformers.git
<br> cd Transformers-Web-H2
<br> mvn spring-boot:run

# How to Test:
Easiest way is use any Browser (since it's a web application) or POSTMAN

1. Browser:
    <ul>
      <li> List: By default, all the transformers present (in mem H2 database) will be displayed in the grid.</li>
      <li> Add: Click the Add button and enter the details of the transformer with an id (Any non negative number which is not already present).</li>
      <li> Update: Double click on the value that you want to update (except id/Overall Rating).</li>
      <li> Delete: Select a record and click Delete button (multiple selection is discouraged).</li>
      <li> Battle: Select multiple records (Transformers) which you want to get into fight and then click Battle button.</li>
    </ul>
2. POSTMAN:
    <ul>
      <li> GET REQUEST: Enter the URL eg. http://localhost:9000/battleground/transformers and press enter.</li>
      <li> POST REQUEST: Enter the URL eg. http://localhost:9000/battleground/goBattle, in the body put the request params eg. [{"id":"1"},{"id":"2"},{"id":"3"}] and press enter.</li>
      <li> DELETE REQUEST: Enter the URL eg. http://localhost:9000/battleground/transformer/1 and press enter.</li>
      <li> PATCH REQUEST: Enter the URL eg. http://localhost:9000/battleground/transformers/3, in the body put the request params eg. {"id":3,"intelligence":10}. Since this is PATCH request only the params which you want to modify should be sent along with the id field.</li>
  </ul>
    
# REST End points:
  <ul>
    <li> List all the Transformers - GET - http://localhost:9000/battleground/transformers </li>
    <li> Add a Transformer - POST - http://localhost:9000/battleground/transformers </li>
    <li> Update a Transformer - PATCH - http://localhost:9000/battleground/transformers/{id} </li>
    <li> Delete a Transformer - DELETE - http://localhost:9000/battleground/transformer/{id} </li>
    <li> Battle - POST - http://localhost:9000/battleground/goBattle </li>
  </ul>

# Assumptions:
  <ul>
    <li>In case of special rules, in which the whole battle ends, it still show the output up until the last fight. If it's the first match then DECEPTIOCON wins and AUTOBOT loses.</li>
    <li>Overall Tie - Only the last match results are considered to display the winners (however, the winning/losing team members from all matches are displayed). </li>
  </ul>
  
# Out of Scope:
  <ul>
    <li>The security, authentication, authorization of the webapp or the REST API is not in scope</li>
    <li>More focus is given on the backend - REST API and not on the frontend EXTJS and hence, there are several gaps in frontend ex. The id needs to be provided even at the time of Transformer creation </li>
  </ul>
  
# Sample Test cases:

<table>
  <tr>
    <th>METHOD</th>
    <th>REQUEST ENDPOINT</th>
    <th>BODY (REQUEST PARAMS)</th>
	<th>RESPONSE</th>
  </tr>
  <tr>
    <td>GET</td>
    <td>List all Transformers http://localhost:9000/battleground/transformers</td>
    <td>NONE</td>
	<td>[
    {
        "id": 1,
        "name": "Bluestreak",
        "type": "AUTOBOT",
        "strength": 6,
        "intelligence": 6,
        "speed": 7,
        "endurance": 9,
        "rank": 5,
        "courage": 2,
        "firepower": 9,
        "skill": 7,
        "overallRating": 37
    },
    {
        "id": 2,
        "name": "Hubcap",
        "type": "AUTOBOT",
        "strength": 4,
        "intelligence": 4,
        "speed": 4,
        "endurance": 4,
        "rank": 4,
        "courage": 4,
        "firepower": 4,
        "skill": 4,
        "overallRating": 20
    },
    {
        "id": 3,
        "name": "Soundwave",
        "type": "DECEPTICON",
        "strength": 8,
        "intelligence": 9,
        "speed": 2,
        "endurance": 6,
        "rank": 7,
        "courage": 5,
        "firepower": 6,
        "skill": 10,
        "overallRating": 31
    }</td>
  </tr>
  <tr>
    <td>POST</td>
    <td>Add a Transformer http://localhost:9000/battleground/transformers</td>
    <td>{
	"id":"4",
    "type": "DECEPTICON",
    "name": "Megatron",
    "strength": 7,
    "intelligence": 7,
    "speed": 7,
    "endurance": 7,
    "rank": 7,
    "courage": 7,
    "firepower": 3,
    "skill": 9
}</td>
	<td>{
    "success": true
}</td>
  </tr>
  <tr>
    <td>PATCH</td>
    <td>Update a Transformer - http://localhost:9000/battleground/transformers/2</td>
    <td>{
	"id":2,
	"strength":10
}</td>
	<td>{
    "success": true
}</td>
  </tr>
  <tr>
    <td>DELETE</td>
    <td>Delete a Transformer - http://localhost:9000/battleground/transformer/1</td>
    <td>NONE</td>
	<td>{
    "success": true
}</td>
  </tr>
  <tr>
    <td>POST</td>
    <td>Battle - http://localhost:9000/battleground/goBattle</td>
    <td>[{
	"id":"1"
},
{
	"id":"2"
},
{
	"id":"3"
},
{
	"id":"4"
}]</td>
	<td>{
    "numberOfBattles": 1,
    "tie": 0,
    "winningTeam": "AUTOBOT",
    "winningTeamMembers": "[Predaking]",
    "losingTeam": "DECEPTICON",
    "losingTeamMembers": "[Soundwave]"
}</td>
  </tr>
  <tr>
    <td>GET</td>
    <td>Homepage of Battleground - http://localhost:9000/battleground/</td>
    <td>NONE</td>
	<td>&lt;html&gt;
    &lt;head&gt;
        &lt;meta charset=&quot;ISO-8859-1&quot;&gt;
        &lt;title&gt;Battle Ground&lt;/title&gt;
        &lt;link href = &quot;https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-neptune/resources/theme-neptune-all.css&quot; 
         rel = &quot;stylesheet&quot; /&gt;
        &lt;script type = &quot;text/javascript&quot; 
         src = &quot;https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/ext-all.js&quot;&gt;&lt;/script&gt;
        &lt;script type=&quot;text/javascript&quot; src=&quot;/js/grid.js&quot;&gt;&lt;/script&gt;
    &lt;/head&gt;
    &lt;body&gt;
	
&lt;/body&gt;
&lt;/html&gt;
	</td>
  </tr>
  <tr>
	<td>GET</td>
	<td>For any other invalid requests - http://localhost:9000/battleground/<any-invalid-request></td>
	<td>NONE</td>
	<td>Invalid Request - Please check the endpoint</td>
  </tr>
</table>
