package com.example.codex;

public class score {

    String team_name;
    String score_no;
    String end_time;

    public score(){};


    public score(String team_name,String score_no,String end_time){
        this.score_no=score_no;
        this.team_name=team_name;
        this.end_time=end_time;
    }



    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getScore_no() {
        return score_no;
    }

    public void setScore_no(String score_no) {
        this.score_no = score_no;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

}
